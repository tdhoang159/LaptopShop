package com.tdhoang.springdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tdhoang.springdemo.model.User;
import com.tdhoang.springdemo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHello(){
        return "Hello from service";
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public List<User> getAllUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User getUserById(long id){
        return this.userRepository.findById(id);
    }

    public User handleSaveUser(User user){
        User newUser = this.userRepository.save(user);
        System.out.println("new user:" + newUser);
        return newUser;
    }

    public void handleDeleteUserById(long id){
        this.userRepository.deleteById(id);
    }
}
