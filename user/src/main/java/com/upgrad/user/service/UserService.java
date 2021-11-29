package com.upgrad.user.service;

import com.upgrad.user.entities.User;

import java.util.Optional;

public interface UserService {
    public User createUser(User user);
    public Optional<User> getUser(int userId);
    public void deleteUser(int userId);
    public User updateUser(int Id,User user);
}
