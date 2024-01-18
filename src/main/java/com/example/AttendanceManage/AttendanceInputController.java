package com.example.AttendanceManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
@Controller
public class AttendanceInputController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping("/attendanceInput")
    public String attendanceInput(@ModelAttribute AttInForm myForm,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        //時刻
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime currentTime = LocalTime.now();
        Time time = Time.valueOf(currentTime.format(dtf));
        //日付
        LocalDate ndate = LocalDate.now();
        int year = ndate.getYear();
        int month = ndate.getMonthValue();
        int day = ndate.getDayOfMonth();
        int userId = user.getId();
        Date date = new Date(year-1900,month-1,day);
        jdbcTemplate.update("INSERT INTO attendances (id,start_time,date) VALUES (?,?,?)", userId,time,date);
        return "redirect:/attendanceList";
    }
}