package backend.car_rental.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import backend.car_rental.entities.Car;
import backend.car_rental.services.ICarService;

@RestController
@RequestMapping("/car")
public class CarController {
    
    @Autowired
    private ICarService carService;

    @GetMapping
    public List<Car> findAll(){

        return carService.findAll();
    }

    @GetMapping("/deleted")
    public List<Car> findAllDeleted(){

        return carService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Car> optionalCar = carService.findById(id);
        if (optionalCar.isPresent()) {
            
            return ResponseEntity.ok().body(optionalCar.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/plate/{plate}")
    public ResponseEntity<?> findByPlate(@PathVariable String plate){
        Optional<Car> optionalCar = carService.findByPlate(plate);
        if (optionalCar.isPresent()) {
            
            return ResponseEntity.ok().body(optionalCar.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car){

        return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(car));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> create(@RequestBody Car car, @PathVariable Long id){
        Optional<Car> optionalCar = carService.findById(id);
        if (optionalCar.isPresent()) {
            car.setId(optionalCar.get().getId());
            
            return ResponseEntity.ok().body(carService.save(car));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Car> optionalCar = carService.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            carService.delete(car);
            return ResponseEntity.ok().body(car);
        }
        return ResponseEntity.notFound().build();
    }

    
}
