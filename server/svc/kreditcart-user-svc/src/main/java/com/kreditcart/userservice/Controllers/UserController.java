package com.kreditcart.userservice.Controllers;

import com.kreditcart.userservice.Dtos.UserDto;
import com.kreditcart.userservice.Models.User;
import com.kreditcart.userservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kreditcart-user-svc/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public UserDto getUserDetails(@PathVariable Long id) {
        User user = this.userService.getUserDetails(id);
        return this.getUserDtoFromUser(user);
    }

    private UserDto getUserDtoFromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
//        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
