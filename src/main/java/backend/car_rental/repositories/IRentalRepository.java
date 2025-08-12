package backend.car_rental.repositories;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import backend.car_rental.entities.Rental;

public interface IRentalRepository extends CrudRepository<Rental,Long>{

    @Query("select r from Rental r where r.end > ?1")
    List<Rental> findRentalsAfterDate(LocalDateTime endDate);

    @Query("select CASE WHEN COUNT(r) > 0 THEN true ELSE false END from Rental r where r.car.id= :carId and (r.start < :end AND r.end > :start)")
    Boolean existsByCarIdAndDates( @Param("start") LocalDateTime start, @Param("end") LocalDateTime end,@Param("carId") Long carId);

    @Query("select CASE WHEN COUNT(r) > 0 THEN true ELSE false END from Rental r where r.car.id= :carId and (r.start < :end AND r.end > :start) and r.id != :rentalId")
    Boolean existsByCarIdAndDatesExceptId( @Param("start") LocalDateTime start, @Param("end") LocalDateTime end,@Param("carId") Long carId , @Param("rentalId") Long rentalId);
}
