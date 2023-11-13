package com.example.AttendanceManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AttendanceListController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //↓これが実行
    @GetMapping("/attendanceList")
    public String index(Model model) {
        String sql = "SELECT * FROM attendances";

        System.out.println(jdbcTemplate.queryForList(sql));
        return "attendanceList";

        //model.addAttribute("name", name);
        //public String index(Model model){
        //String sql = "Select id,begin_time,end_time form attendances ";
        //List<Map<String, Object>> attendances = this.jdbcTemplate.queryForList(sql);
        //System.out.println(attendances);
        //model.addAttribute("attendances", attendances);

        /*28まで
        public String attendanceList(@RequestParam(name="name", required=false, defaultValue="中塚") String name, Model model) {
            //String sql = "SELECT * FROM ATTENDANCES";
            model.addAttribute("name", name);
            //public String index(Model model){
            //String sql = "Select id,begin_time,end_time form attendances ";
            //List<Map<String, Object>> attendances = this.jdbcTemplate.queryForList(sql);
            //System.out.println(attendances);
            //model.addAttribute("attendances", attendances);
            return "attendanceList";  */
    }

}