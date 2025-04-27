package com.kreditcart.userservice.Services;

import com.kreditcart.userservice.Dtos.AddressRequestDto;
import com.kreditcart.userservice.Dtos.AddressResponseDto;
import com.kreditcart.userservice.Exceptions.ResourceNotFoundException;
import com.kreditcart.userservice.Models.Address;
import com.kreditcart.userservice.Models.City;
import com.kreditcart.userservice.Models.User;
import com.kreditcart.userservice.Repositories.AddressRepository;
import com.kreditcart.userservice.Repositories.CityRepository;
import com.kreditcart.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;

    public AddressResponseDto addAddress(UUID userId, AddressRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with given id %s", userId)));

        UUID cityId = requestDto.getCityId();
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("City not found with given id %s", cityId)));

        Address address = new Address();
        address.setUser(user);
        address.setCity(city);
        address.setLine1(requestDto.getLine1());
        address.setLine2(requestDto.getLine2());
        address.setLandMark(requestDto.getLandMark());
        address.setZipCode(requestDto.getZipCode());
        address.setLocation(requestDto.getLocation());

        Address savedAddress = addressRepository.save(address);

        return mapToResponseDto(savedAddress);
    }

    public List<AddressResponseDto> getUserAddresses(UUID userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private AddressResponseDto mapToResponseDto(Address address) {
        return new AddressResponseDto(
                address.getId(),
                address.getLine1(),
                address.getLine2(),
                address.getLandMark(),
                address.getZipCode(),
                address.getCity().getName(),
                address.getLocation()
        );
    }
}
