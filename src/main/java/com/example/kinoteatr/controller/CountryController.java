package com.example.kinoteatr.controller;

import com.example.kinoteatr.model.Country;
import com.example.kinoteatr.repo.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/countries")
@PreAuthorize("hasAnyAuthority('CASHIER', 'ADMIN')")
public class CountryController {

    private final CountryRepo countryRepo;

    @Autowired
    public CountryController(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    @GetMapping()
    public String listCountries(Model model) {
        Iterable<Country> countries = countryRepo.findAll();
        model.addAttribute("countries", countries);
        return "countries/allCountries";
    }

    @GetMapping("/addCountry")
    public String showAddClassForm(Model model) {
        Country country = new Country();
        //clas.setPublicationYear(0);
        model.addAttribute("country", country);
        return "countries/addCountry";
    }

    @PostMapping("/addCountry")
    public String addCountry(@Valid @ModelAttribute("country") Country country, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "countries/addCountry";
        }
        System.out.println("Name: " + country.getName_country());
        countryRepo.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/editCountry/{id}")
    public String showEditCountryForm(@PathVariable("id") long id, Model model) {
        Country country = countryRepo.findById(id).orElse(null);
        if (country == null) {
            return "redirect:/countries";
        }
        model.addAttribute("country", country);
        return "countries/editCountry";
    }

    @PostMapping("/editCountry/{id}")
    public String editCountry(@PathVariable("id") int id, @Valid @ModelAttribute("country") Country country, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "countries/editCountry"; // Останется на странице редактирования с отображением ошибок
        }
        country.setId_country(id);
        countryRepo.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/deleteCountry/{id}")
    public String deleteCountry(@PathVariable("id") long id) {
        countryRepo.deleteById(id);
        return "redirect:/countries";
    }
}
