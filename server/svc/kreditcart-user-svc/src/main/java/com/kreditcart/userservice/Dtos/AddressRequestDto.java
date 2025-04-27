package com.kreditcart.userservice.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kreditcart.userservice.Models.GeoLocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {
    private String line1;
    private String line2;
    private String landMark;
    private String zipCode;
    private GeoLocation location;
    private UUID cityId;
}
