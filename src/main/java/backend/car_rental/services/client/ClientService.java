package backend.car_rental.services.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import backend.car_rental.entities.Client;
import backend.car_rental.repositories.IClientRepository;
import backend.car_rental.services.client.interfaces.IClientService;

@Service
public class ClientService implements IClientService{

    @Autowired
    private IClientRepository clientRepository;
    
    // @Override
    // public List<Client> findAll() {
    //     return (List<Client>) clientRepository.findAllActive();

    // }

    // @Override
    // public List<Client> findAllDeleted() {
    //     return (List<Client>) clientRepository.findAllDeleted();

    // }

    // @Override
    // public Optional<Client> findById(Long id) {

    //     return clientRepository.findActiveById(id);
    // }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(Client client) { //Eliminado logico
        client.setDeleted(true);
        clientRepository.save(client);
    }

    @Override
    public Optional<Client> findByDni(Long dni) {

        return clientRepository.findByDni(dni);
    }

    @Override
    public List<Client> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<Client> findAllDeleted() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllDeleted'");
    }

    @Override
    public Optional<Client> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    

}
