package com.example.AttendanceManage;

import java.sql.Time;
import java.util.Date;

public class Attendance {
    private int id;
    private Date date;
    private Time start_time;
    private Time end_time;
    private Time break_start;
    private Time break_end;
    private Time total_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public Time getBreak_start() {
        return break_start;
    }

    public void setBreak_start(Time break_start) {
        this.break_start = break_start;
    }

    public Time getBreak_end() {
        return break_end;
    }

    public void setBreak_end(Time break_end) {
        this.break_end = break_end;
    }
}