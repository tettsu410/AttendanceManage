package com.example.AttendanceManage;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// ユーザー認証で使用するユーザーデータモデルです
// UserDetailsクラスを実装します。
public class MyUserDetails implements UserDetails {

    MyUserDetails(int id , String username) {
        this.setUsername(username);
        this.setId(id);
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 権限情報を返す（例えば、USER）
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }
}
