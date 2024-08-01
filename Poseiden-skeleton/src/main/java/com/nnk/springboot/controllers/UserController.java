package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;

import com.nnk.springboot.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/user")

public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService= userService;
    }
    @RequestMapping("/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/add")
    public String addUser(User bid) {
        return "user/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            
            userService.save(user);
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        try{
            model.addAttribute("user", userService.findById(id));
            return "user/update";
        } catch (RequestedObjectNotFoundException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "404";
        }
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }
        user.setId(id);
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.delete(id);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
    @GetMapping("/loginPage")
    public String getMethodName() {
        return "loginPage";
    }
    
}
