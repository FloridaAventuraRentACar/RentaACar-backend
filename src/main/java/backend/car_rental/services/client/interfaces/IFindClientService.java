package backend.car_rental.services.client.interfaces;

import java.util.List;
import java.util.Optional;


import backend.car_rental.entities.Client;

public interface IFindClientService {
    
    List<Client> findAll();

    Optional<Client> findById(Long id);
    // Optional<Client> findByDni(Long dni);
}
