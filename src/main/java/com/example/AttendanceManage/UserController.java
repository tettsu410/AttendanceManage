package com.example.AttendanceManage;

// UserController.java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> userList = new ArrayList<>();

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", userList);
        return "admin";
    }

    @GetMapping("/admin/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(User user) {
        userList.add(user);
        return "redirect:/admin";
    }
}

