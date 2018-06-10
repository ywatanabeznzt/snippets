package jp.zero.oauthserver.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private int userId;
    private String username;
    private String password;
    private String email;
    private int age;

    public User(int userId, String username, String password, String email, int age) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("USER"));
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAge() {
        return this.age;
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
}
