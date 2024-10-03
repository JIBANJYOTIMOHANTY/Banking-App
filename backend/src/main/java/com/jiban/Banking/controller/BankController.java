package com.jiban.Banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiban.Banking.entity.Admin;
import com.jiban.Banking.entity.Users;
import com.jiban.Banking.services.AdminService;
import com.jiban.Banking.services.JwtService;
import com.jiban.Banking.services.UsersService;

@RestController
public class BankController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Bank"   ;
    }

    @PostMapping("/signUp")
    public String addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    @PostMapping("/signIn")
    public String login(@RequestBody Admin admin){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));
        
        if(authenticate.isAuthenticated()){
            return jwtService.generatetoken(admin.getUsername()) + "\nSignIn Successful";
        } else {
            throw new RuntimeException("username or password is invalid");
        }
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addUsers(@RequestBody Users users){
        usersService.createUsers(users);
        return "User created Successfully";

    }
}
