package backend.car_rental.repositories;


import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import backend.car_rental.entities.PriceAdjustment;

public interface IPriceAdjustmentRepository extends JpaRepository<PriceAdjustment, Long> {
    
    @Query("select CASE WHEN COUNT(r) > 0 THEN true ELSE false END from PriceAdjustment r where r.periodStart <= :periodEnd AND r.periodEnd >= :periodStart")
    boolean existsOverlappingPeriod(LocalDate periodStart, LocalDate periodEnd);
}
