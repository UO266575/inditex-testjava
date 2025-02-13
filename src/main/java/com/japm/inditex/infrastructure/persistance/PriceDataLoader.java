package com.japm.inditex.infrastructure.persistance;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class PriceDataLoader {
    private final PriceRepository priceRepository;

    @PostConstruct
    public void initDatabase() {
        priceRepository.deleteAll();

        List<PriceRepositoryEntity> prices = List.of(
                new PriceRepositoryEntity(null, 1L,
                        LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        1, 35455L, 0, new BigDecimal("35.50"), "EUR"),

                new PriceRepositoryEntity(null, 1L,
                        LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                        LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                        2, 35455L, 1, new BigDecimal("25.45"), "EUR"),

                new PriceRepositoryEntity(null, 1L,
                        LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                        LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                        3, 35455L, 1, new BigDecimal("30.50"), "EUR"),

                new PriceRepositoryEntity(null, 1L,
                        LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        4, 35455L, 1, new BigDecimal("38.95"), "EUR")
        );

        priceRepository.saveAll(prices);
    }
}
