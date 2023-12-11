package com.example.AttendanceManage;

import com.example.AttendanceManage.form.SampleForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Sample01Controller {

    @GetMapping("/sample01")
    public String sample01Show(HttpServletRequest request, Model model) {
        // セッションを取得する（引数:Trueの場合は新しいセッションを開始する）
        // 例）ログインに成功したらセッションを開始する
        HttpSession session = request.getSession(true);

        return "sample01";
    }

    // ユーザーIDと名前の入力処理
    // 引数でsample01のフォームに入力された値を
    // SampleFormクラスのmyFormオブジェクトに代入して受け取る
    @PostMapping("/sample01")
    public String sample01Input(HttpServletRequest request, @ModelAttribute SampleForm myForm, Model model) {
        //https://qiita.com/NariseT/items/172ca093364aa9391989
        // セッションを取得する
        HttpSession session = request.getSession(false);

        if(session == null){
            // セッションがない場合はGetメソッドでsample01へ戻る
            return "sample01";
        } else {
            // セッションがある場合はセッションへデータをセットする

            // SpringBootでは@ModelAttributeを使用することでHTMLのFormタグで入力されたデータを
            // 指定したクラスから生成されたインスタンスの属性に自動的に代入することができる。
            // 例）sample01.htmlのformで入力された値をSampleFormクラスのインスタンスmyFormにセットする
            String id = myForm.getUserId();
            String name = myForm.getUserName();

            // セッションにFormで入力された値を追加する
            session.setAttribute("userId", id);
            session.setAttribute("userName", name);

            // sample02を呼び出す
            return "redirect:/sample02";
        }
    }
}
