package com.example.AttendanceManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

// ユーザー認証を処理するクラスです。
// AbstractUserDetailsAuthenticationProviderクラスを継承します。
// retrieveUserメソッドをオーバーライドして実装します。
@Service
public class MyUserDetailsService extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

        String sql = "SELECT id,username,pass FROM USERTABLE where username = ? and pass = ?";
        List<User> usertable = null;
        try {
            usertable = jdbcTemplate.query(sql, new DataClassRowMapper<>(User.class), userId, password);
        }catch (Exception ex) {
            System.out.println(ex.toString());
        }

            if (usertable.stream().count() == 1) {
                // ログイン成功
                // DBから取得したIDも使用します。
                user = new MyUserDetails(usertable.get(0).getId(), usertable.get(0).getUsername());
            } else {
                // ログイン失敗
                throw new UsernameNotFoundException("ユーザ認証に失敗しました。");
                //throw new BadCredentialsException("ユーザ認証に失敗しました。");
            }

        // UserDetailsのオブジェクトを戻り値とします
        return user;
    }
}