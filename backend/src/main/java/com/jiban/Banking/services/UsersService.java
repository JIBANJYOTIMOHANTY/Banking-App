package com.jiban.Banking.services;

import java.util.List;

import com.jiban.Banking.entity.Users;

public interface UsersService {
    public String createUsers(Users users);
    public List<Users> getAllUsers();
    public Users getUsersById(Integer id);
    public String updateUsers(Integer id, Users users);
    public String deleteUsers(Integer id);
}
