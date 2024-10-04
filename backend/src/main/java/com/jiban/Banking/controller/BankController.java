package com.jiban.Banking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiban.Banking.entity.Admin;
import com.jiban.Banking.entity.Users;
import com.jiban.Banking.repository.UsersRepository;
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
            return jwtService.generatetoken(admin.getUsername()) ;
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

    @GetMapping("getAll/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @PutMapping("/update/user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateUsers(@PathVariable Integer id, @RequestBody Users users){
        String message = usersService.updateUsers(id, users);
        return message;
    }

    @DeleteMapping("/delete/user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable Integer id) throws Exception{
        return usersService.deleteUsers(id);
    }

    

}
