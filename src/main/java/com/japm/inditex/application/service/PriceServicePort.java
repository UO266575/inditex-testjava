package com.japm.inditex.application.service;

import com.japm.inditex.domain.models.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceServicePort {
    Optional<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
