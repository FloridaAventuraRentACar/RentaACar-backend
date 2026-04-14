package backend.car_rental.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.car_rental.dto.car.CreateCarDto;
import backend.car_rental.dto.car.PartialUpdateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.services.car.interfaces.ICarPartialUpdateService;
import backend.car_rental.services.car.interfaces.IUpdateCarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class UpdateCarController {
    
    private final IUpdateCarService updateCarService;
    private final ICarPartialUpdateService partialUpdateCarService;
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCarDto> updateCar(@Valid @RequestBody CreateCarDto carDto, @PathVariable Long id){
      return ResponseEntity.ok(updateCarService.updateCar(id,carDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseCarDto> partialUpdateCar(@Valid @RequestBody PartialUpdateCarDto carDto, @PathVariable Long id){
      return ResponseEntity.ok(partialUpdateCarService.partialUpdateCar(id,carDto));
    }
}
