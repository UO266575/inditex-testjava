package com.japm.inditex.application.service;

import com.japm.inditex.domain.exceptions.InvalidParamsException;
import com.japm.inditex.domain.exceptions.PriceNotFoundException;
import com.japm.inditex.domain.models.Price;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PriceService {

    private final PriceServicePort priceServicePort;

    public Price getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        checkApplicablePriceParams(productId, brandId, applicationDate);
        return priceServicePort
                .getApplicablePrice(
                        productId, brandId, applicationDate).orElseThrow(() ->
                                new PriceNotFoundException(productId, brandId, applicationDate));
    }

    private void checkApplicablePriceParams(Long productId, Long brandId, LocalDateTime applicationDate) {
        if (isInvalidId(productId) || isInvalidId(brandId) || applicationDate == null) {
            throw new InvalidParamsException("All params are mandatory and id greater than 0.");
        }
    }

    private boolean isInvalidId(Long id) {
        return id == null || id < 0;
    }
}
