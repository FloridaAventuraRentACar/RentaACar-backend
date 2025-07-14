package backend.car_rental.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.client.CreateClientDto;
import backend.car_rental.entities.Client;
import backend.car_rental.services.client.interfaces.IFindClientService;
import backend.car_rental.services.client.interfaces.ISaveClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IFindClientService findClientService;
    
    @Autowired
    private ISaveClientService saveClientService;

    @GetMapping
    public List<Client> findAll(){

        return findClientService.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CreateClientDto client, BindingResult result){

        return saveClientService.save(client, result);
    }

    @PostMapping("/list")
    public ResponseEntity<?> createAll(@Valid @RequestBody List<CreateClientDto> clientList, BindingResult result){

        return saveClientService.saveAll(clientList, result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(){
        return null; //Implementation missing
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> findByDni(@PathVariable Long dni){
        return null; //Implementation missing
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Long id){
        return null; //Implementation missing
    }
}
