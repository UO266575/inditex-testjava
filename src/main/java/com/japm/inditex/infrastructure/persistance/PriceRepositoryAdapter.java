package com.japm.inditex.infrastructure.persistance;

import com.japm.inditex.application.service.PriceServicePort;
import com.japm.inditex.domain.models.Price;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PriceRepositoryAdapter implements PriceServicePort {
    private final PriceRepository priceRepository;

    @Override
    public Optional<Price> getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return priceRepository
                .findPriceByProductIdBrandIdAndDate(productId, brandId, applicationDate)
                .map(this::convertToDomain);
    }

    private Price convertToDomain(PriceRepositoryEntity price) {
        return new Price(
                price.getId(),
                price.getBrandId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPriceList(),
                price.getProductId(),
                price.getPriority(),
                price.getPrice(),
                price.getCurrency()
        );
    }
}
