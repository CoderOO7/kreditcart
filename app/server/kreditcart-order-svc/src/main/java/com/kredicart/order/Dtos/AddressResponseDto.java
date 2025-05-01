package com.kredicart.order.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {
    private UUID id;
    private String line1;
    private String line2;
    private String landMark;
    private String zipCode;
    private String cityName;
    private String latitude;
    private String longitude;
    private UUID userId;
}