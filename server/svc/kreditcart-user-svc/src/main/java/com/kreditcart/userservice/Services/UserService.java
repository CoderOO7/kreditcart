package com.kreditcart.userservice.Services;

import com.kreditcart.userservice.Models.User;
import com.kreditcart.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User getUserDetails(@PathVariable Long id) {
        return this.userRepository.findById(id).get();
    }

}
