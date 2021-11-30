package com.upgrad.user.service;

import com.upgrad.user.entities.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User getUser(int userId);
    public List<User> getAllUsers();
    public void deleteUser(int userId);
    public User updateUser(int Id,User user);
}
