package com.example.kinoteatr.controller;

import com.example.kinoteatr.model.Status;
import com.example.kinoteatr.repo.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/status")
@PreAuthorize("hasAnyAuthority('CASHIER', 'ADMIN')")
public class StatusController {
    private final StatusRepo statusRepo;

    @Autowired
    public StatusController(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    @GetMapping()
    public String listStatus(Model model) {
        Iterable<Status> status = statusRepo.findAll();
        model.addAttribute("status", status);
        return "status/allStatus";
    }

    @GetMapping("/addStatus")
    public String showAddClassForm(Model model) {
        Status status = new Status();
        //clas.setPublicationYear(0);
        model.addAttribute("status", status);
        return "status/addStatus";
    }

    @PostMapping("/addStatus")
    public String addStatus(@Valid @ModelAttribute("status") Status status, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "status/addStatus";
        }
        System.out.println("Name: " + status.getName_status());
        statusRepo.save(status);
        return "redirect:/status";
    }

    @GetMapping("/editStatus/{id}")
    public String showEditStatusForm(@PathVariable("id") long id, Model model) {
        Status status = statusRepo.findById(id).orElse(null);
        if (status == null) {
            return "redirect:/status";
        }
        model.addAttribute("status", status);
        return "status/editStatus";
    }

    @PostMapping("/editStatus/{id}")
    public String editStatus(@PathVariable("id") int id, @Valid @ModelAttribute("status") Status status, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "status/editStatus"; // Останется на странице редактирования с отображением ошибок
        }
        status.setId_status(id);
        statusRepo.save(status);
        return "redirect:/status";
    }

    @GetMapping("/deleteStatus/{id}")
    public String deleteStatus(@PathVariable("id") long id) {
        statusRepo.deleteById(id);
        return "redirect:/status";
    }
}
