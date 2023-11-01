package com.example.kinoteatr.controller;

import com.example.kinoteatr.model.Genre;
import com.example.kinoteatr.repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/genres")
@PreAuthorize("hasAnyAuthority('CASHIER', 'ADMIN')")
public class GenreController {
    private final GenreRepo genreRepo;

    @Autowired
    public GenreController(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @GetMapping()
    public String listGenres(Model model) {
        Iterable<Genre> genres = genreRepo.findAll();
        model.addAttribute("genres", genres);
        return "genres/allGenres";
    }

    @GetMapping("/addGenre")
    public String showAddClassForm(Model model) {
        Genre genre = new Genre();
        model.addAttribute("genre", genre);
        return "genres/addGenre";
    }

    @PostMapping("/addGenre")
    public String addGenre(@Valid @ModelAttribute("genre") Genre genre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genres/addGenre";
        }
        System.out.println("Name: " + genre.getName_genre());
        genreRepo.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/editGenre/{id}")
    public String showEditGenreForm(@PathVariable("id") long id, Model model) {
        Genre genre = genreRepo.findById(id).orElse(null);
        if (genre == null) {
            return "redirect:/genres";
        }
        model.addAttribute("genre", genre);
        return "genres/editGenre";
    }

    @PostMapping("/editGenre/{id}")
    public String editGenre(@PathVariable("id") int id, @Valid @ModelAttribute("genre") Genre genre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genres/editGenre"; // Останется на странице редактирования с отображением ошибок
        }
        genre.setId_genre(id);
        genreRepo.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/deleteGenre/{id}")
    public String deleteGenre(@PathVariable("id") long id) {
        genreRepo.deleteById(id);
        return "redirect:/genres";
    }
}
