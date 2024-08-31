package backend.car_rental.services;

import java.util.List;
import java.util.Optional;

import backend.car_rental.entities.Rental;

public interface IRentalService {

    List<Rental> findAll();
    List<Rental> findAllDeleted();

    Optional<Rental> findById(Long id);

    Rental save(Rental rental);

    void delete(Rental rental);
}
