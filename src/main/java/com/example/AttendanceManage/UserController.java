package com.example.AttendanceManage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class UserController {

    @GetMapping("/user")
    public String showUserPage() {
        return "user";
    }

    @PostMapping("/userManagement")
    public String userManagement(@RequestParam String action,
                                 @RequestParam String name,
                                 @RequestParam String pass,
                                 @RequestParam String userId,
                                 Model model) {
        UserManagement userManagement = new UserManagement();
        try {
            userManagement.doPost(action, name, pass, userId);
            model.addAttribute("message", "Action completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Error performing action.");
        }
        return "user";
    }
}

