package backend.car_rental.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import backend.car_rental.entities.Car;
import backend.car_rental.entities.Rental;

public interface IRentalRepository extends CrudRepository<Rental,Long>{
    
    @Query("select r from Rental r where r.deleted = false")
    List<Rental> findAllActive();

    @Query("select r from Rental r where r.deleted = true")
    List<Rental> findAllDeleted();

    @Query("select r from Rental r where r.id = ?1 and r.deleted = false")
    Optional<Rental> findActiveById(Long id);
}
