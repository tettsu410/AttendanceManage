package com.example.AttendanceManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Controller
public class AttendanceBreakEndController {
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
    @PostMapping("/attendanceBreakEnd")
    public String attendanceBreakEnd(@ModelAttribute AttInForm myForm,Model model){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime currentTime = LocalTime.now();
        int userId = myForm.getUserId();
        Time time = Time.valueOf(currentTime.format(dtf));
        jdbcTemplate.update("INSERT INTO attendances (id,break_end) VALUES (?,?)", userId,time);
        return "redirect:/attendanceList";
    }
}