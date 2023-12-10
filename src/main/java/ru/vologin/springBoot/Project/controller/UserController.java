package ru.vologin.springBoot.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vologin.springBoot.Project.model.User;
import ru.vologin.springBoot.Project.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers (Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new")
    public String newUser (Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String add (@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit (@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String update (@ModelAttribute ("user") User user, @RequestParam(value = "id") long id) {
        userService.update(user, id);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete (@RequestParam(value = "id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}