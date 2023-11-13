package com.example.AttendanceManage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactAddressController {
    //これが実行
    @GetMapping("/contactAddress")
    public String jokyoList(@RequestParam(name="name", required=false, defaultValue="test") String name, Model model) {
        model.addAttribute("name", name);
        return "contactAddress";
    }

}