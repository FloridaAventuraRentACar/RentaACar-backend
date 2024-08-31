package backend.car_rental.controlers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/deleted")
    public ResponseEntity<?> findAllDeleted(){
        return ResponseEntity.ok().body(rentalService.findAllDeleted());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Rental> optionalRental = rentalService.findById(id);
        if (optionalRental.isPresent()) {
            
            return ResponseEntity.ok().body(optionalRental.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Rental> create(@RequestBody Rental rental){
        return ResponseEntity.ok().body(rentalService.save(rental));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Rental rental, @PathVariable Long id){

        Optional<Rental> optionalRental = rentalService.findById(id);

        if (optionalRental.isPresent()) {
            rental.setId(optionalRental.get().getId());
            return ResponseEntity.ok().body(rentalService.save(rental));
        }    

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Rental> optionalRental = rentalService.findById(id);
        if (optionalRental.isPresent()) {
            Rental rental = optionalRental.get();
            rentalService.delete(rental);
            return ResponseEntity.ok().body(rental);
        }
        return ResponseEntity.notFound().build();
    }



}
