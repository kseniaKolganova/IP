package com.example.kinoteatr.controller;

import com.example.kinoteatr.model.*;
import com.example.kinoteatr.repo.AuthorRepo;
import com.example.kinoteatr.repo.FilmRepo;
import com.example.kinoteatr.repo.GenreRepo;
import com.example.kinoteatr.repo.ManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/films")
@PreAuthorize("hasAnyAuthority('CASHIER', 'ADMIN')")
public class FilmController {
    @Autowired
    public ManufacturerRepo manufacturerRepo;
    @Autowired
    private FilmRepo filmRepo;
    @Autowired
    private GenreRepo genreRepo;
    @Autowired
    private AuthorRepo authorRepo;

    @GetMapping()
    public String listManu(Model model) {
        Iterable<Film> films = filmRepo.findAll();
        model.addAttribute("films", films);
        return "films/allFilms";
    }

    @GetMapping("/addFilm")
    public String showAddClassForm(Model model) {
        Film film = new Film();
        film.setYear_film(2000);
        film.setTime_film(2);
        film.setNumbers_actors_film(1);
        film.setRating_film(1);

        Iterable<Manufacturer> manufacturers = manufacturerRepo.findAll();
        model.addAttribute("manufacturers", manufacturers);

        Iterable<Genre> genres = genreRepo.findAll();
        model.addAttribute("genres", genres);

        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);

        model.addAttribute("film", film);
        return "films/addFilm";
    }

    @PostMapping("/addFilm")
    public String addManu(@Valid @ModelAttribute("film") Film film, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "films/addFilm";
        }
        System.out.println("Name: " + film.getName_film());
        filmRepo.save(film);
        return "redirect:/films";
    }

    @GetMapping("/editFilm/{id}")
    public String showEditManuForm(@PathVariable("id") long id, Model model) {
        Film film = filmRepo.findById(id).orElse(null);
        if (film == null) {
            return "redirect:/films";
        }

        Iterable<Manufacturer> manufacturers = manufacturerRepo.findAll();
        model.addAttribute("manufacturers", manufacturers);

        Iterable<Genre> genres = genreRepo.findAll();
        model.addAttribute("genres", genres);

        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);

        model.addAttribute("film", film);
        return "films/editFilm";
    }

    @PostMapping("/editFilm/{id}")
    public String editManu(@PathVariable("id") long id, @Valid @ModelAttribute("film") Film film, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "films/editFilm"; // Останется на странице редактирования с отображением ошибок
        }
        film.setId_film(id);
        filmRepo.save(film);
        return "redirect:/films";
    }


    @GetMapping("/deleteFilm/{id}")
    public String deleteManu(@PathVariable("id") long id) {
        filmRepo.deleteById(id);
        return "redirect:/films";
    }
}
