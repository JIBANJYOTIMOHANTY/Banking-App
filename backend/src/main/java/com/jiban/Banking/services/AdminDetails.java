package com.jiban.Banking.services;

import java.util.Collection;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jiban.Banking.entity.Admin;

public class AdminDetails implements UserDetails {
    private String username;
    private String password;
    List<GrantedAuthority> authorities;

    public AdminDetails(Admin admin){
        username = admin.getUsername();
        password = admin.getPassword();
        authorities = Arrays.stream(admin.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    
}
