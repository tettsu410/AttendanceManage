package com.example.AttendanceManage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Sample02Controller {

    // Sample01で入力された値を表示します。
    @GetMapping("/sample02")
    public String sample01Input(HttpServletRequest request, Model model) {
        // セッションを取得する
        HttpSession session = request.getSession(false);

        if(session == null){
            // セッションがない場合はGetメソッドでsample01へ戻る
            return "sample01";
        } else {
            // sample01でセットしたセッションのデータを取得
            String id =  session.getAttribute("userId").toString();
            String name =  session.getAttribute("userName").toString();

            // セッションから習得したデータをモデルに追加する（画面へ表示するため）
            model.addAttribute("user", id + ":" + name);
            return "sample02";
        }
    }

}
