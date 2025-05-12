package com.example.KR.controller;
import com.example.KR.models.User;
import com.example.KR.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("errorMessage", null);
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute User user, Model model) {
        if (!userService.createUsers(user)) {
            model.addAttribute("errorMessage", "Пользователь с таким именем или email уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}
