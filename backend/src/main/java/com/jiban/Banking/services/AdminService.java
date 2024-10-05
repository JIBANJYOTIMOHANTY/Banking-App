package com.jiban.Banking.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jiban.Banking.entity.Admin;
import com.jiban.Banking.repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admins = adminRepository.findByUsername(username);
        return admins.map(AdminDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
    }

    Admin admin2 = new Admin();
    public String addAdmin(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        System.out.println(admin.getRole());
        if (admin.getRole() == null){
            admin.setRole("ADMIN");
        }
        adminRepository.save(admin);
        return "Admin added successfully";
    }

}
