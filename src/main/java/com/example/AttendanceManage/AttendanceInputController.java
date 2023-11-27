package com.example.AttendanceManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Time;
@Controller
public class AttendanceInputController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping("/attendanceInput")
    public String attendanceInput(Model model){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime currentTime = LocalTime.now();
        Time time = Time.valueOf(currentTime.format(dtf));
        jdbcTemplate.update("INSERT INTO attendances (id,start_time) VALUES (?,?)", 5,time);
        return "redirect:/attendanceList";
    }
}