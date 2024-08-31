package backend.car_rental.services;

import java.util.List;
import java.util.Optional;


import backend.car_rental.entities.Client;

public interface IClientService {
    
    List<Client> findAll();
    List<Client> findAllDeleted();

    Optional<Client> findById(Long id);
    Optional<Client> findByDni(Long dni);
    
    Client save(Client client);
    void delete(Client client);
    
}
