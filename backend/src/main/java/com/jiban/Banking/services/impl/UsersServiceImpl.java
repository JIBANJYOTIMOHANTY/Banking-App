package com.jiban.Banking.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jiban.Banking.entity.Admin;
import com.jiban.Banking.entity.Users;
import com.jiban.Banking.repository.AdminRepository;
import com.jiban.Banking.repository.UsersRepository;
import com.jiban.Banking.services.AdminDetails;
import com.jiban.Banking.services.UsersService;

public class UsersServiceImpl implements UsersService, UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users createUsers(Users users) {
        return null;
    }

    @Override
    public List<Users> getAllUsers() {
        return null;
    }

    @Override
    public void getUsersById(int id) {
        
    }

    @Override
    public String updateUsers(Long id, Users users) {
        return null;
    }

    @Override
    public void deleteUsers(Long id) {
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admins = adminRepository.findByName(username);
        return admins.map(AdminDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
    }
    
}
