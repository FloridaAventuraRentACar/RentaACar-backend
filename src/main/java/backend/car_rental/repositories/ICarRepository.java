package backend.car_rental.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import backend.car_rental.entities.Car;
import java.util.*;
public interface ICarRepository extends CrudRepository<Car,Long>{
    
    @Query("select c from Car c where c.plate = ?1 and c.deleted = false")
    Optional<Car> findByPlate(String plate);

    @Query("select c from Car c where c.deleted = false")
    List<Car> findAllActive();

    @Query("select c from Car c where c.deleted = true")
    List<Car> findAllDeleted();


    @Query("select c from car c where c.id = ?1 and c.deleted = false")
    Optional<Car> findActiveById(Long id);
    
}
