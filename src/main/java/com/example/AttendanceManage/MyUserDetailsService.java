package com.example.AttendanceManage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// ユーザー認証を処理するクラスです。
// AbstractUserDetailsAuthenticationProviderクラスを継承します。
// retrieveUserメソッドをオーバーライドして実装します。
@Service
public class MyUserDetailsService extends AbstractUserDetailsAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    // データベースや他の認証ソースからユーザー情報を取得するロジックを実装
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String userId = username;
        String password = (String) authentication.getCredentials();

        // 下記の認証処理をDBのデータを使用するに変更します。
        MyUserDetails user = null;
        if(username.equals("user") && password.equals("pass")){
            // ログイン成功
            // DBから取得したIDも使用します。
            user = new MyUserDetails(1,userId);
        } else {
            // ログイン失敗
            throw new UsernameNotFoundException("ユーザ認証に失敗しました。");
            //throw new BadCredentialsException("ユーザ認証に失敗しました。");
        }

        // UserDetailsのオブジェクトを戻り値とします
        return user;
    }
}