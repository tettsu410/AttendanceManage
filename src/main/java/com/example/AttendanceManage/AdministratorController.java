package com.example.AttendanceManage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdministratorController {

    @GetMapping("/admin")
    public String adminHome(Model model) {
        // ダミーデータを使用している例
        List<User> userList = UserService.getAllUsers();
        model.addAttribute("users", userList);
        return "admin";
    }

    @GetMapping("/admin/addUser")
    public String showAddUserForm() {
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute User user) {
        // ユーザーの追加処理（データベースへの保存など）を行う
        UserService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/logout")
    public String logout() {
        // ログアウト処理（セッションの無効化など）
        return "redirect:/login";
    }
}
