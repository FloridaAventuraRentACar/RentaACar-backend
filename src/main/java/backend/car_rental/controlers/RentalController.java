package backend.car_rental.controlers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.dto.rental.UpdateRentalDto;
import backend.car_rental.services.rental.interfaces.IFindRentalService;
import backend.car_rental.services.rental.interfaces.IRentalDeleteService;
import backend.car_rental.services.rental.interfaces.ISaveRentalService;
import backend.car_rental.services.rental.interfaces.IUpdateRentalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rentals")
@AllArgsConstructor
public class RentalController {
    
    private ISaveRentalService saveRentalService;
    private IFindRentalService findRentalService;
    private IUpdateRentalService updateRentalService;
    private IRentalDeleteService deleteRentalService;

    @PostMapping
    public ResponseEntity<ResponseRentalDto> create(@Valid @RequestBody CreateRentalDto rentalDto){
        return ResponseEntity.ok().body(saveRentalService.save(rentalDto));
    }

    @GetMapping("/current")
    public ResponseEntity<?> findCurrentRentals(){

        return findRentalService.findCurrentRentals(); 
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findRentalById(@PathVariable Long id){
        return findRentalService.findRentalById(id); 
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseRentalDto> update(@Valid @RequestBody UpdateRentalDto rentalDto, @PathVariable Long id){
        return ResponseEntity.ok().body(updateRentalService.update(rentalDto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        deleteRentalService.delete(id);

        return ResponseEntity.ok().body("Rental deleted successfully"); 
    }

    @GetMapping
    public ResponseEntity<List<ResponseRentalDto>> findAll(){

        return ResponseEntity.ok().body(findRentalService.findAll()); 
    }
}
