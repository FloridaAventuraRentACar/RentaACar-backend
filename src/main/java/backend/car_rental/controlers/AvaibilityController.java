package backend.car_rental.controlers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.services.car.interfaces.ICarGetAvailableService;

@RestController
@RequestMapping("/availability")
public class AvaibilityController {

    @Autowired
    private ICarGetAvailableService carService;

    @GetMapping
    public ResponseEntity<List<ResponseCarDto>> getAvailability(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime){
    //El formato de LocalDateTime es: "2024-08-30T14:30:00"
    
        return ResponseEntity.ok(carService.getAvailableCars(startDateTime, endDateTime));
    }
}
