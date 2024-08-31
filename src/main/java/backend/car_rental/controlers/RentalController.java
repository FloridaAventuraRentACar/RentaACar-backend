package backend.car_rental.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.entities.Rental;
import backend.car_rental.services.IRentalService;

@RestController
@RequestMapping("/rental")
public class RentalController {
    
    @Autowired
    private IRentalService rentalService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(rentalService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Rental rental){
        return ResponseEntity.ok().body(rentalService.save(rental));
    }

}
