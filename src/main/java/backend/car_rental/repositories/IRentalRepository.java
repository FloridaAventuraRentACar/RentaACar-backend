package backend.car_rental.repositories;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import backend.car_rental.entities.Rental;

public interface IRentalRepository extends CrudRepository<Rental,Long>{

    @Query("select r from Rental r where r.end > ?1")
    List<Rental> findRentalsAfterDate(LocalDateTime endDate);
}
