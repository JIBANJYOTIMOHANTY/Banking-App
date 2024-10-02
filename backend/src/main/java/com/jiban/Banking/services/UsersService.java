package com.jiban.Banking.services;

import java.util.List;

import com.jiban.Banking.entity.Users;

public interface UsersService {
    public Users createUsers(Users users);
    public List<Users> getAllUsers();
    public void getUsersById(int id);
    public String updateUsers(Long id, Users users);
    public void deleteUsers(Long id);
}
