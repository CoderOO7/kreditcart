package com.kreditcart.userservice.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ValidateRequestDto {
    String token;
    UUID userId;
}
