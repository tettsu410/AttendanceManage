package com.example.AttendanceManage;

import java.sql.Time;

public class Attendance {
    private int id;
    private Time start_time;

    private Time end_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getBegin_time() {
        return start_time;
    }

    public void setBegin_time(Time begin_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
}
