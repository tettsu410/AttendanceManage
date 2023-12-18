package com.example.AttendanceManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;


@Controller
public class AttendanceEndController {
    // ドライバーのクラス名
    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    // JDMC接続先情報
    private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:8080";
    // ユーザー名
    //private static final String USER = "ユーザー名";
    // パスワード
    //private static final String PASS = "パスワード";
    // 接続先の情報を null
    Connection connection = null;
    // 問い合わせ取得結果を null
    Statement statement = null;
    // 実行結果のデータを　null
    ResultSet resultSet = null;


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping("/attendanceEnd")
    public String attendanceEnd(@ModelAttribute AttInForm myForm,Model model){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime currentTime = LocalTime.now();
        LocalDate ndate = LocalDate.now();
        int year = ndate.getYear();
        int month = ndate.getMonthValue();
        int day = ndate.getDayOfMonth();
        int userId = myForm.getUserId();
        Date date = new Date(year-1900,month-1,day);
        Time time = Time.valueOf(currentTime.format(dtf));
        jdbcTemplate.update("UPDATE attendances SET end_time=? WHERE id=? AND date=?",time,userId,date);


        List<Map<String, Object>> start = jdbcTemplate.queryForList("SELECT start_time FROM attendances WHERE id=? AND date=?",userId,date);
        //start.forEach(System.out::println);
        List<Map<String, Object>> end = jdbcTemplate.queryForList("SELECT end_time FROM attendances WHERE id=? AND date=?",userId,date);
        //end.forEach(System.out::println);
        System.out.println(start);
        System.out.println(end);

        //jdbcTemplate.update("SELECT start_time FROM attendances WHERE id=? AND date=?",userId,date);
        //Period period = Period.between(start.toLocalDate(), end.toLocalDate());
        //Duration duration = Duration.between(start, time);
        //System.out.println(duration);
        //long diff = duration.toHours();
        //jdbcTemplate.update("UPDATE attendances SET time=? WHERE id=? AND date=?",list,userId,date);
        return "redirect:/attendanceList";
    }
}