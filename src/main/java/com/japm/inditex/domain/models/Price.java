package com.japm.inditex.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
        Long id,
        Long brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Long productId,
        Integer priority,
        BigDecimal price,
        String currency
) {
}