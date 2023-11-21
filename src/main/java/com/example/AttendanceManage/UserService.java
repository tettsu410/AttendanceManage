package com.example.AttendanceManage;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List<User> userList = new ArrayList<>();

    public static List<User> getAllUsers() {
        // ダミーデータを返す例
        return userList;
    }

    public static void addUser(User user) {
        // ユーザーの追加処理（データベースへの保存など）を行う
        userList.add(user);
    }
}

