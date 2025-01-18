package backend.car_rental.services.rental;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.car_rental.entities.Rental;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.rental.interfaces.IRentalService;

@Service
public class RentalService implements IRentalService {

    @Autowired
    private IRentalRepository rentalRepository;

    @Override
    public List<Rental> findAll() {
        return (List<Rental>)rentalRepository.findAll();
    }
    
    // @Override
    // public List<Rental> findAllDeleted() {
    //     return (List<Rental>)rentalRepository.findAllDeleted();
    // }


    // @Override
    // public Optional<Rental> findById(Long id) {
    //     return rentalRepository.findActiveById(id);
      
    // }

    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public void delete(Rental rental) {
        rental.setDeleted(true);
        rentalRepository.save(rental);
    }

    @Override
    public List<Rental> findAllDeleted() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllDeleted'");
    }

    @Override
    public Optional<Rental> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
