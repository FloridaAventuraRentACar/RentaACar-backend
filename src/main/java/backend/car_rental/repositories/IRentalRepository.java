package backend.car_rental.repositories;


import org.springframework.data.repository.CrudRepository;

import backend.car_rental.entities.Rental;

public interface IRentalRepository extends CrudRepository<Rental,Long>{


    // @Query(select r from )
    // List<Rental> findByDates(LocalDateTime start, LocalDateTime end);
}
