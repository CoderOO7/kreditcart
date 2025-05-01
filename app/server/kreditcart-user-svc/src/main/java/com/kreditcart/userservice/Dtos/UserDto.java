package com.kreditcart.userservice.Dtos;

import lombok.Getter;
import lombok.Setter;
import com.kreditcart.userservice.Models.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;
    private String email;
//    private Set<Role> roles = new HashSet<>();
}
