package com.kreditcart.userservice.Repositories;

import com.kreditcart.userservice.Models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
}
