package backend.car_rental.services.rental;

import org.springframework.stereotype.Service;

import backend.car_rental.exceptions.NotFoundException;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.rental.interfaces.IRentalDeleteService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalDeleteService implements IRentalDeleteService {
    
    private final IRentalRepository repository;

    @Override
    public void delete(Long id) {
        
        if (!repository.existsById(id)) {
            throw new NotFoundException("Rental not found");
        }
        
        repository.deleteById(id);
    }
}
