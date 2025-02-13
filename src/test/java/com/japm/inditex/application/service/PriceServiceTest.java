package com.japm.inditex.application.service;

import com.japm.inditex.domain.exceptions.InvalidParamsException;
import com.japm.inditex.domain.exceptions.PriceNotFoundException;
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
class PriceServiceTest {

    @Mock
    private PriceServicePort priceServicePort;

    @InjectMocks
    private PriceService priceService;

    private final Long VALID_PRODUCT_ID = 12345L;
    private final Long VALID_BRAND_ID = 1L;
    private final LocalDateTime VALID_DATE = LocalDateTime.of(2020, 6, 14, 10, 0);
    private Price mockPrice;

    @BeforeEach
    void setUp() {
        mockPrice = new Price(1L, VALID_BRAND_ID, VALID_DATE, VALID_DATE.plusDays(10), 1, VALID_PRODUCT_ID,
                1,new BigDecimal("35.50"), "EUR");
    }

    @Test
    void shouldReturnApplicablePrice_whenPriceExists() {
        when(priceServicePort.getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE))
                .thenReturn(Optional.of(mockPrice));

        Price result = priceService.getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);

        assertNotNull(result);
        assertEquals(mockPrice, result);

        verify(priceServicePort, times(1))
                .getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);
    }

    @Test
    void shouldThrowPriceNotFoundException_whenPriceDoesNotExist() {
        when(priceServicePort.getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE))
                .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () ->
                priceService.getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE));

        verify(priceServicePort, times(1)).getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, VALID_DATE);
    }

    @Test
    void shouldThrowInvalidParamsException_whenInvalidParams() {
        assertThrows(
                InvalidParamsException.class, () -> priceService.getApplicablePrice(null, VALID_BRAND_ID, VALID_DATE));
        assertThrows(
                InvalidParamsException.class, () -> priceService.getApplicablePrice(-1L, VALID_BRAND_ID, VALID_DATE));
        assertThrows(
                InvalidParamsException.class, () -> priceService.getApplicablePrice(VALID_PRODUCT_ID, null, VALID_DATE));
        assertThrows(
                InvalidParamsException.class, () -> priceService.getApplicablePrice(VALID_PRODUCT_ID, -1L, VALID_DATE));
        assertThrows(
                InvalidParamsException.class, () -> priceService.getApplicablePrice(VALID_PRODUCT_ID, VALID_BRAND_ID, null));

        verify(priceServicePort, never()).getApplicablePrice(anyLong(), anyLong(), any());
    }
}