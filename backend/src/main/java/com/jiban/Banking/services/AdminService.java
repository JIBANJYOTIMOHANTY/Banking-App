package com.jiban.Banking.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jiban.Banking.entity.Admin;
import com.jiban.Banking.repository.AdminRepository;

public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admins = adminRepository.findByName(username);
        return admins.map(AdminDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
    }

    public String addAdmin(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return "Admin added successfully";
    }

}
