package backend.car_rental.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import backend.car_rental.entities.Car;
public interface ICarRepository extends CrudRepository<Car,Long>{

    @Query("select c from Car c where c.id NOT IN (SELECT r.car.id from Rental r where ((r.start BETWEEN ?1 and ?2) or (r.end BETWEEN ?1 and ?2) or (?1 BETWEEN r.start and r.end) or (?2 BETWEEN r.start and r.end)))")
    List<Car> getAvailableCars(LocalDateTime startDateTime , LocalDateTime endDateTime);
    
}
