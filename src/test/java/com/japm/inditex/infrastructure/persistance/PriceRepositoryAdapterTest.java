package com.japm.inditex.infrastructure.persistance;

import com.japm.inditex.domain.models.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    private final Long VALID_PRODUCT_ID = 12345L;
    private final Long VALID_BRAND_ID = 1L;
    private final LocalDateTime VALID_DATE = LocalDateTime.of(2020, 6, 14, 10, 0);
    private Price expectedPrice;
    private PriceRepositoryEntity mockPriceEntity;

    @BeforeEach
    void setUp() {
        expectedPrice = new Price(1L, VALID_BRAND_ID, VALID_DATE, VALID_DATE.plusDays(10), 1, VALID_PRODUCT_ID,
                1,new BigDecimal("35.50"), "EUR");
        mockPriceEntity = new PriceRepositoryEntity(1L, VALID_BRAND_ID, VALID_DATE, VALID_DATE.plusDays(10), 1, VALID_PRODUCT_ID,
                1,new BigDecimal("35.50"), "EUR");
    }

    @Test
    void shouldReturnPrice_whenPriceExists() {
        when(priceRepository.findPriceByProductIdBrandIdAndDate(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE))
                .thenReturn(Optional.of(mockPriceEntity));
        Optional<Price> result = priceRepositoryAdapter.getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);

        assertTrue(result.isPresent());
        assertEquals(expectedPrice, result.get());

        verify(priceRepository, times(1)).findPriceByProductIdBrandIdAndDate(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);
    }

    @Test
    void shouldReturnEmpty_whenPriceDoesNotExist() {
        when(priceRepository.findPriceByProductIdBrandIdAndDate(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE))
                .thenReturn(Optional.empty());

        Optional<Price> result = priceRepositoryAdapter.getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);

        assertFalse(result.isPresent());

        verify(priceRepository, times(1)).findPriceByProductIdBrandIdAndDate(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);
    }
}