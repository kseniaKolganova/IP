package com.example.kinoteatr.controller;

import com.example.kinoteatr.model.Author;
import com.example.kinoteatr.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AuthorController {

    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorController(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @GetMapping()
    public String listAuthors(Model model) {
        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);
        return "authors/allAuthors";
    }

    @GetMapping("/addAuthor")
    public String showAddClassForm(Model model) {
        Author author = new Author();
        //clas.setPublicationYear(0);
        model.addAttribute("author", author);
        return "authors/addAuthor";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@Valid @ModelAttribute("author") Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "authors/addAuthor";
        }
        System.out.println("Name: " + author.getName_author());
        authorRepo.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/editAuthor/{id}")
    public String showEditAuthorForm(@PathVariable("id") long id, Model model) {
        Author author = authorRepo.findById(id).orElse(null);
        if (author == null) {
            return "redirect:/authors";
        }
        model.addAttribute("author", author);
        return "authors/editAuthor";
    }

    @PostMapping("/editAuthor/{id}")
    public String editAuthor(@PathVariable("id") int id, @Valid @ModelAttribute("author") Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "authors/editAuthor"; // Останется на странице редактирования с отображением ошибок
        }
        author.setId_author(id);
        authorRepo.save(author);
        return "redirect:/authors"; // Перенаправление на страницу со всеми героями
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable("id") long id) {
        authorRepo.deleteById(id);
        return "redirect:/authors";
    }
}