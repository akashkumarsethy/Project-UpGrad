package com.upgrad.user.service;

import com.upgrad.user.entities.User;
import com.upgrad.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImpl(){}

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(int userId, User user) {
        Optional<User> userFromDb = userRepository.findById(userId);
        userFromDb.setFirstname("Akash Kumar");
        userFromDb.setLastname("Sethy");
        userFromDb.setCity("Cuttack");
        userRepository.save(userFromDb);
        return  userFromDb;
    }
}
