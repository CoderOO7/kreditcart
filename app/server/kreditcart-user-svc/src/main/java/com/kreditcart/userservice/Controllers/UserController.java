package com.kreditcart.userservice.Controllers;

import com.kreditcart.userservice.Dtos.AddressRequestDto;
import com.kreditcart.userservice.Dtos.AddressResponseDto;
import com.kreditcart.userservice.Dtos.UserDto;
import com.kreditcart.userservice.Services.AddressService;
import com.kreditcart.userservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    AddressService addressService;

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable UUID id) {
        UserDto user = this.userService.getUserDetails(id);
        return new ResponseEntity<>(user, HttpStatus.OK) ;
    }

    @GetMapping("{userId}/addresses")
    public ResponseEntity<List<AddressResponseDto>> getUserAddresses(@PathVariable UUID userId) {
        List<AddressResponseDto> addresses = addressService.getUserAddresses(userId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PostMapping("{userId}/addresses")
    public ResponseEntity<AddressResponseDto> addAddress(@PathVariable UUID userId, @RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDto responseDto = addressService.addAddress(userId, addressRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
