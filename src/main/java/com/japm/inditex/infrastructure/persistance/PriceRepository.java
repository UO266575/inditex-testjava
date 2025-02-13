package com.japm.inditex.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<PriceRepositoryEntity, Long> {

    @Query("""
                SELECT p FROM PriceEntity p
                WHERE p.productId = :productId
                AND p.brandId = :brandId
                AND :applicationDate BETWEEN p.startDate AND p.endDate
                ORDER BY p.priority DESC
                LIMIT 1
            """)
    Optional<PriceRepositoryEntity> findPriceByProductIdBrandIdAndDate(
            Long productId, Long brandId, LocalDateTime applicationDate);
}
