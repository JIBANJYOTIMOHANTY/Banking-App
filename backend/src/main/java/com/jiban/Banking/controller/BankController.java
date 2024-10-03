package com.jiban.Banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiban.Banking.entity.Admin;
import com.jiban.Banking.services.AdminService;
import com.jiban.Banking.services.JwtService;

@RestController
public class BankController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Bank"   ;
    }

    @PostMapping("/signUp")
    public String addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    
}
