package com.kreditcart.userservice.Services;

import com.kreditcart.userservice.Dtos.UserDto;
import com.kreditcart.userservice.Exceptions.ResourceNotFoundException;
import com.kreditcart.userservice.Models.User;
import com.kreditcart.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserDto getUserDetails(@PathVariable UUID id) {
        User user =  this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("User not found with given id %s", id)));
        return getUserDtoFromUser(user);
    }

    private UserDto getUserDtoFromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
//        userDto.setRoles(user.getRoles());
        return userDto;
    }

}
