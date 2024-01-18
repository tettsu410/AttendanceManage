package com.example.AttendanceManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
    private NamedParameterJdbcTemplate jdbcTemplate;
    @PostMapping("/attendanceEnd")
    public String attendanceEnd(@ModelAttribute AttInForm myForm,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        System.out.println(user.getId());
        System.out.println(user.getUsername());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime currentTime = LocalTime.now();
        LocalDate ndate = LocalDate.now();
        int year = ndate.getYear();
        int month = ndate.getMonthValue();
        int day = ndate.getDayOfMonth();
        int userId = user.getId();
        Date date = new Date(year-1900,month-1,day);
        Time time = Time.valueOf(currentTime.format(dtf));

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("date", date);
        params.put("end_time", time);

        // 更新処理
        jdbcTemplate.update("UPDATE attendances SET end_time=:end_time WHERE id=:userId AND date=:date",params);

        //データの取得
        /*params.remove("end_time");
        String sql = "SELECT start_time FROM attendances WHERE id=:userId AND date=:date";
        Time start= jdbcTemplate.queryForObject(sql,params,Time.class);

        //List<Map<String, Object>> end = jdbcTemplate.query("SELECT end_time FROM attendances WHERE id=? AND date=?",userId,date);

        Duration totaltime = Duration.between(start.toLocalTime(), time.toLocalTime());
        //long minusminute = totaltime.toMinutes() - totaltime.toHours() * 60;
        String totaltimeinput = String.valueOf(totaltime.toHours()) +":"+ String.valueOf(totaltime.toMinutes());
        //params.put("userId", userId);
        //params.put("date", date);
        params.put("totaltimeinput", totaltimeinput);
        // 更新処理
        jdbcTemplate.update("UPDATE attendances SET totaltime=:totaltimeinput WHERE id=:userId AND date=:date",params);

        //System.out.println(start);
        //System.out.println(time);*/
        return "redirect:/attendanceList";
    }
}