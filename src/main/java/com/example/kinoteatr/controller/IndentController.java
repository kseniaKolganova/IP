package com.example.kinoteatr.controller;

import com.example.kinoteatr.model.Film;
import com.example.kinoteatr.model.Indent;
import com.example.kinoteatr.model.ModelUser;
import com.example.kinoteatr.model.Status;
import com.example.kinoteatr.repo.FilmRepo;
import com.example.kinoteatr.repo.IndentRepo;
import com.example.kinoteatr.repo.StatusRepo;
import com.example.kinoteatr.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/indents")
@PreAuthorize("hasAnyAuthority('CASHIER', 'ADMIN', 'USER')")
public class IndentController {

    private static final Logger logger = LoggerFactory.getLogger(IndentController.class);

    @Autowired
    public IndentRepo indentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private FilmRepo filmRepo;

    @GetMapping()
    public String listManu(Model model) {
        Iterable<Indent> indents = indentRepo.findAll();
        model.addAttribute("indents", indents);
        return "indents/allIndents";
    }

    @GetMapping("/addIndent")
    public String showAddClassForm(Model model) {
        Indent indent = new Indent();

        Iterable<Status> statuses = statusRepo.findAll();
        model.addAttribute("statuses", statuses);

        Iterable<ModelUser> users = userRepo.findAll();
        model.addAttribute("users", users);

        model.addAttribute("indent", indent);
        return "indents/addIndent";
    }

    @PostMapping("/addIndent")
    public String addManu(@Valid @ModelAttribute("indent") Indent indent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "indents/addIndent";
        }
        indentRepo.save(indent);
        return "redirect:/indents";
    }

    @GetMapping("/editIndent/{id}")
    public String showEditManuForm(@PathVariable("id") long id, Model model) {
        Indent indent = indentRepo.findById(id).orElse(null);
        if (indent == null) {
            return "redirect:/indents";
        }

        Iterable<Status> statuses = statusRepo.findAll();
        model.addAttribute("statuses", statuses);

        Iterable<ModelUser> users = userRepo.findAll();
        model.addAttribute("users", users);

        model.addAttribute("indent", indent);
        return "indents/editIndent";
    }

    @PostMapping("/editIndent/{id}")
    public String editManu(@PathVariable("id") long id, @Valid @ModelAttribute("indent") Indent indent, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "indents/editIndent"; // Останется на странице редактирования с отображением ошибок
        }
        indent.setId_indent(id);
        indentRepo.save(indent);
        return "redirect:/indents";
    }


    @GetMapping("/deleteIndent/{id}")
    public String deleteManu(@PathVariable("id") long id) {
        indentRepo.deleteById(id);
        return "redirect:/indents";
    }

    @GetMapping("/addFilmToIndent")
    public String showAddFilmToIndentPage(Model model) {
        Iterable<Film> film = filmRepo.findAll();
        model.addAttribute("film", film);

        Iterable<Indent> indent = indentRepo.findAll();
        model.addAttribute("indent", indent);
        return "indents/addFilmToIndent"; // Название HTML-шаблона для этой страницы
    }

    @PostMapping("/addFilmToIndent")
    public String addFilmToIndent(@RequestParam long indent, @RequestParam String film, RedirectAttributes redirectAttributes)
    {
        Indent indent2 = indentRepo.findByName(indent);
        Film film2 = filmRepo.findByName(film);
        indent2.getFilm().add(film2);
        film2.getIndent().add(indent2);
        indentRepo.save(indent2);

        // Добавьте атрибут "success" для сообщения об успешном добавлении
        redirectAttributes.addFlashAttribute("success", "Фильм успешно добавлено в заказ");
            return "redirect:/indents";
    }


}
