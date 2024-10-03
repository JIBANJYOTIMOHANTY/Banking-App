package com.jiban.Banking.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jiban.Banking.entity.Users;
import com.jiban.Banking.repository.UsersRepository;
import com.jiban.Banking.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService  {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public String createUsers(Users users) {
        usersRepository.save(users);
        return "User Created Successfully.";
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUsersById(Integer id) {
        return usersRepository.findById(id).get();   
    }

    @Override
    public String updateUsers(Integer id, Users users) {
        Optional<Users> user = usersRepository.findById(id);
        if(user.isPresent()){
            Users foundUser = user.get();
            foundUser.setName(users.getName());
            foundUser.setBalance(users.getBalance());
            foundUser.setEmail(users.getEmail());
        } else {
            new RuntimeException("User not found with specified id : " + id);
        }
        return "User updated successfully.";
    }

    @Override
    public String deleteUsers(Integer id) {
        usersRepository.deleteById(id);
        return "User deleted successfully.";
    }

    
}
