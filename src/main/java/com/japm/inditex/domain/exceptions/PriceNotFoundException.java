package com.japm.inditex.domain.exceptions;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Long productId, Long brandId, LocalDateTime applicationDate) {
        super(String.format("No price found for Product ID: %d, Brand ID: %d on Date: %s",
                productId, brandId, applicationDate));
    }
}
