package backend.car_rental.services.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import backend.car_rental.entities.Client;
import backend.car_rental.repositories.IClientRepository;
import backend.car_rental.services.client.interfaces.IFindClientService;

@Service
public class FindClientService implements IFindClientService{

    @Autowired
    private IClientRepository clientRepository;
    
    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();

    }

    @Override
    public Optional<Client> findById(Long id) {

        return clientRepository.findById(id);
    }

    

    // @Override
    // public Optional<Client> findByDni(Long dni) {

    //     return clientRepository.findByDni(dni);
    // }
    

}
