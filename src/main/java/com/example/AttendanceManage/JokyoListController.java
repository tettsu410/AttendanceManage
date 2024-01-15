package com.example.AttendanceManage;

import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;

@Controller
public class JokyoListController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/jokyoList")
    public String attendances(Model model) {
        String sql = "SELECT id,date,start_time,end_time,break_start,break_end FROM ATTENDANCES where id = 1";

        List<Attendance> attendances = jdbcTemplate.query(sql,new DataClassRowMapper<>(Attendance.class));
        model.addAttribute("attendances",attendances);
        return "jokyoList";
    }
}

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

//@Controller
//public class JokyoListController {
//これが実行
//  @GetMapping("/jokyoList")
//public String jokyoList(@RequestParam(name="name", required=false, defaultValue="test") String name, Model model) {
//  model.addAttribute("name", name);
// return "jokyoList";
//}

//}