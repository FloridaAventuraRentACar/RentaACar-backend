package backend.car_rental.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import backend.car_rental.dto.car.CreateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.services.car.interfaces.IDeleteCarService;
import backend.car_rental.services.car.interfaces.IFindCarService;
import backend.car_rental.services.car.interfaces.ISaveCarService;
import jakarta.validation.Valid;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/cars")
public class CarController {
    
    @Autowired
    private IFindCarService findCarService;
    @Autowired
    private ISaveCarService saveCarService;
    @Autowired
    private IDeleteCarService deleteCarService;

    @GetMapping
    public ResponseEntity<List<ResponseCarDto>> findAll(){

        return findCarService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        return findCarService.findById(id);
    }

    // @GetMapping("/plate/{plate}")
    // public ResponseEntity<?> findByPlate(@PathVariable String plate){
    //     Optional<Car> optionalCar = findCarService.findByPlate(plate);
    //     if (optionalCar.isPresent()) {
            
    //         return ResponseEntity.ok().body(optionalCar.get());
    //     }
    //     return ResponseEntity.notFound().build();
    // }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateCarDto carDto , BindingResult result){

        return saveCarService.save(carDto, result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CreateCarDto carDto, BindingResult result, @PathVariable Long id){
      return saveCarService.update(carDto,result ,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        return deleteCarService.delete(id);
    }

    
}
