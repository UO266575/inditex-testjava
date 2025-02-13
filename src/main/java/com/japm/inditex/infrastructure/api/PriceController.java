package com.japm.inditex.infrastructure.api;

import com.japm.inditex.api.PricesApi;
import com.japm.inditex.application.service.PriceService;
import com.japm.inditex.domain.models.Price;
import com.japm.inditex.model.PriceControllerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RestController
@AllArgsConstructor
public class PriceController implements PricesApi {

    private final PriceService priceService;

    @Override
    public ResponseEntity<PriceControllerDTO> _getApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId) {
        return ResponseEntity.ok(
                convertToReponse(priceService.getApplicablePrice(productId, brandId, applicationDate))
        );
    }

    private PriceControllerDTO convertToReponse(Price price) {
        return new PriceControllerDTO(price.brandId(),price.startDate(),price.endDate(),price.priceList(),
                price.productId(), price.price(), price.currency());
    }


}
