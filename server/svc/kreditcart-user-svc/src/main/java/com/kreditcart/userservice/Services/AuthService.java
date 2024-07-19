package com.kreditcart.userservice.Services;

import com.kreditcart.userservice.Models.User;
import com.kreditcart.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    public User userSignup(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            User savedUser = userRepository.save(user);
            return savedUser;
        }

        return userOptional.get();
    }

    public User userLogin(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        if(!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }
}
