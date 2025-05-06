package backend.car_rental.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import backend.car_rental.entities.Client;
import backend.car_rental.services.client.interfaces.IClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @GetMapping
    public List<Client> findAll(){

        return clientService.findAll();
    }

    @GetMapping("/deleted")
    public List<Client> findAllDeleted(){
        
        return clientService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Client> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            
            return ResponseEntity.ok().body(optionalClient.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> findByDni(@PathVariable Long dni){
        Optional<Client> optionalClient = clientService.findByDni(dni);
        if (optionalClient.isPresent()) {
            
            return ResponseEntity.ok().body(optionalClient.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client){

        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Long id){
        Optional<Client> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            client.setId(optionalClient.get().getId());
            
            return ResponseEntity.ok().body(clientService.save(client));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Client> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            clientService.delete(client);
            return ResponseEntity.ok().body(client);
        }
        return ResponseEntity.notFound().build();
    }
}
