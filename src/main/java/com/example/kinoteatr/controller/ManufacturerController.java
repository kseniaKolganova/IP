package com.example.kinoteatr.controller;


import com.example.kinoteatr.model.Country;
import com.example.kinoteatr.model.Manufacturer;
import com.example.kinoteatr.repo.CountryRepo;
import com.example.kinoteatr.repo.ManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/manufacturers")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ManufacturerController {
    @Autowired
    public ManufacturerRepo manufacturerRepo;
    @Autowired
    private CountryRepo countryRepo;


    @GetMapping()
    public String listManu(Model model) {
        Iterable<Manufacturer> manufacturers = manufacturerRepo.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturers/allManufacturers";
    }

    @GetMapping("/addManufacturer")
    public String showAddClassForm(Model model) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setYear_manufacturer(2000);

        Iterable<Country> countries = countryRepo.findAll();
        model.addAttribute("countries", countries);

        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/addManufacturer";
    }

    @PostMapping("/addManufacturer")
    public String addManu(@Valid @ModelAttribute("manufacturer") Manufacturer manufacturer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "manufacturers/addManufacturer";
        }
        System.out.println("Name: " + manufacturer.getName_manufacturer());
        manufacturerRepo.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/editManufacturer/{id}")
    public String showEditManuForm(@PathVariable("id") long id, Model model) {
        Manufacturer manufacturer = manufacturerRepo.findById(id).orElse(null);
        if (manufacturer == null) {
            return "redirect:/manufacturers";
        }

        Iterable<Country> countries = countryRepo.findAll();
        model.addAttribute("countries", countries);

        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/editManufacturer";
    }

    @PostMapping("/editManufacturer/{id}")
    public String editManu(@PathVariable("id") int id, @Valid @ModelAttribute("manufacturer") Manufacturer manufacturer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "manufacturers/editManufacturer"; // Останется на странице редактирования с отображением ошибок
        }
        manufacturer.setId_manufacturer(id);
        manufacturerRepo.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/deleteManufacturer/{id}")
    public String deleteManu(@PathVariable("id") long id) {
        manufacturerRepo.deleteById(id);
        return "redirect:/manufacturers";
    }
}
