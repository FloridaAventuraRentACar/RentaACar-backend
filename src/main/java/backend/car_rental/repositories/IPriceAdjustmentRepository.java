package backend.car_rental.repositories;


import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import backend.car_rental.entities.PriceAdjustment;

public interface IPriceAdjustmentRepository extends JpaRepository<PriceAdjustment, Long> {
    
    @Query("select CASE WHEN COUNT(p) > 0 THEN true ELSE false END from PriceAdjustment p where p.periodStart <= :periodEnd AND p.periodEnd >= :periodStart AND p.active = true")
    boolean existsOverlappingPeriod(LocalDate periodStart, LocalDate periodEnd);

    @Query("select p from PriceAdjustment p where p.periodStart <= :periodEnd AND p.periodEnd >= :periodStart AND p.active = true")
    List<PriceAdjustment> findOverlappingsPeriod(LocalDate periodStart, LocalDate periodEnd);
}
 