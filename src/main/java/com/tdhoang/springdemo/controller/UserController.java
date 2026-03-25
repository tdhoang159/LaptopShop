package com.tdhoang.springdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tdhoang.springdemo.model.User;
import com.tdhoang.springdemo.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUserByEmail("demo@gmail.com");
        System.out.println("arrUsers: " + arrUsers);
        model.addAttribute("demo", "test");
        model.addAttribute("demo1", "hello from controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User userDetails = this.userService.getUserById(id);
        model.addAttribute("userDetails", userDetails);
        return "admin/user/update-info";
    }

    @PostMapping("/admin/user/update")
    public String updateUserInfo(Model model, @ModelAttribute("userDetails") User userDetails) {
        User updatedUser = this.userService.getUserById(userDetails.getId());
        if (updatedUser != null) {
            updatedUser.setEmail(userDetails.getEmail());
            updatedUser.setFullName(userDetails.getFullName());
            updatedUser.setAddress(userDetails.getAddress());
            updatedUser.setPhone(userDetails.getPhone());

            this.userService.handleSaveUser(updatedUser);
        }
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserInfoPage(Model model, @PathVariable long id) {
        User userDetails = this.userService.getUserById(id);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("id", id);
        return "admin/user/show-info-details";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        User deleteUser = this.userService.getUserById(id);
        model.addAttribute("deleteUser", deleteUser);
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@ModelAttribute("deleteUser") User deleteUser){
        this.userService.handleDeleteUserById(deleteUser.getId());
        return "redirect:/admin/user";
    }
}
