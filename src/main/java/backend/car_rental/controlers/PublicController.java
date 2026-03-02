package backend.car_rental.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.services.car.interfaces.ICarGetAvailableService;
import backend.car_rental.services.car.interfaces.IFindCarService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@AllArgsConstructor
@RestController
@RequestMapping("/public")
public class PublicController {
    
    private final IFindCarService findCarService;

    private ICarGetAvailableService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<ResponseCarDto>> findAllCars() {
        return findCarService.findAll();
    }
    
    @GetMapping("/availability")
    public ResponseEntity<List<ResponseCarDto>> getAvailability(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime){
    //El formato de LocalDateTime es: "2024-08-30T14:30:00"
    
        return ResponseEntity.ok(carService.getAvailableCars(startDateTime, endDateTime));
    }

}
