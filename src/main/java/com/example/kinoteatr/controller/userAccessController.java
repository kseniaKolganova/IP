package com.example.kinoteatr.controller;

import com.example.kinoteatr.model.ModelUser;
import com.example.kinoteatr.model.RoleEnum;
import com.example.kinoteatr.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class userAccessController {

    @Autowired
    private UserRepo userRepository;

    @GetMapping("/edit-access")
    public String editAccess(Model model) {
        // Получите список всех пользователей из репозитория
        List<ModelUser> allUsers = (List<ModelUser>) userRepository.findAll();

        // Передайте полученные данные в модель и отобразите их на странице редактирования доступа
        model.addAttribute("users", allUsers);
        return "edit-access"; // Замените на имя вашего шаблона
    }

    @PostMapping("/edit-access/{userId}/edit")
    public String updateAccess(@PathVariable long userId, @RequestParam Set<RoleEnum> roles) {
        // Получите пользователя по ID
        Optional<ModelUser> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            ModelUser user = optionalUser.get();
            user.setRoles(roles);
            userRepository.save(user);
        }

        return "redirect:/edit-access";
    }

}
