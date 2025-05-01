package com.kredicart.order.Repositories;

import com.kredicart.order.Models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
}
