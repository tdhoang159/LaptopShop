package com.tdhoang.springdemo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tdhoang.springdemo.model.User;
import com.tdhoang.springdemo.service.UploadFileService;
import com.tdhoang.springdemo.service.UserService;


@Controller
public class UserController {
    private final UserService userService;
    private final UploadFileService uploadFileService;

    public UserController(UserService userService, UploadFileService uploadFileService) {
        this.userService = userService;
        this.uploadFileService = uploadFileService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUserByEmail("demo@gmail.com");
        System.out.println("arrUsers: " + arrUsers);
        model.addAttribute("demo", "test");
        model.addAttribute("demo1", "hello from controller with model");
        return "hello";
    }

    @RequestMapping("/admin/users")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/view";
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
        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserInfoPage(Model model, @PathVariable long id) {
        User userDetails = this.userService.getUserById(id);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("id", id);
        return "admin/user/info-details";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUser(Model model,
            @ModelAttribute("newUser") User user,
            @RequestParam("avatar_file") MultipartFile file) {
        String avatar = this.uploadFileService.handleSaveUploadFile(file, "avatar");
        // this.userService.handleSaveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        User deleteUser = this.userService.getUserById(id);
        model.addAttribute("deleteUser", deleteUser);
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@ModelAttribute("deleteUser") User deleteUser) {
        this.userService.handleDeleteUserById(deleteUser.getId());
        return "redirect:/admin/users";
    }
}
