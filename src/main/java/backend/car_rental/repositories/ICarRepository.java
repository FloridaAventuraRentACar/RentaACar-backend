package backend.car_rental.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import backend.car_rental.entities.Car;
import java.util.*;
public interface ICarRepository extends CrudRepository<Car,Long>{
    
    @Query("select c from Car c where c.plate = ?1 and c.deleted = false")
    Optional<Car> findByPlate(String plate);


    
}
