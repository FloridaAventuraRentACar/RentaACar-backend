package backend.car_rental.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import backend.car_rental.entities.Car;
public interface ICarRepository extends CrudRepository<Car,Long>{

    @Query("select c from Car c where c.id NOT IN (SELECT r.car.id from Rental r where (r.start < :end AND r.end > :start))")
    List<Car> getAvailableCars(@Param("start") LocalDateTime start ,@Param("end") LocalDateTime end);
    
}
