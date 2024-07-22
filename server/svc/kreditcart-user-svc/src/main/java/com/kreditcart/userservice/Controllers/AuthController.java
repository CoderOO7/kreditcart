package com.kreditcart.userservice.Controllers;

import com.kreditcart.userservice.Dtos.LoginRequestDto;
import com.kreditcart.userservice.Dtos.SignupRequestDto;
import com.kreditcart.userservice.Dtos.UserDto;
import com.kreditcart.userservice.Dtos.ValidateRequestDto;
import com.kreditcart.userservice.Models.User;
import com.kreditcart.userservice.Services.AuthService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("kreditcart-user-svc/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        User user = authService.userSignup(signupRequestDto.getEmail(), signupRequestDto.getPassword());
        UserDto userDto = getUserDtoFromUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Pair<User, MultiValueMap<String, String>> bodyWithHeaders = authService.userLogin(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            UserDto userDto = getUserDtoFromUser(bodyWithHeaders.a);
            return new ResponseEntity<>(userDto, bodyWithHeaders.b, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestBody ValidateRequestDto validateRequestDto) {
        Boolean isValid = authService.validateToken(validateRequestDto.getToken(), validateRequestDto.getUserId());
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }

    private UserDto getUserDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
//        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
