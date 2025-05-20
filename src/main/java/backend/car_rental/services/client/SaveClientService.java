package backend.car_rental.services.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.client.CreateClientDto;
import backend.car_rental.entities.Client;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.ClientMapper;
import backend.car_rental.repositories.IClientRepository;
import backend.car_rental.services.client.interfaces.ISaveClientService;

@Service
public class SaveClientService implements ISaveClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public ResponseEntity<?> save(CreateClientDto clientDto, BindingResult result) {
        if (result.hasErrors()) {
            return Errors.returnSintaxErrors(result);
        }
        Client clientToSave = ClientMapper.toEntity(clientDto);

        return ResponseEntity.ok(ClientMapper.tDto(clientRepository.save(clientToSave)));
    }

    @Override
    public ResponseEntity<?> saveAll(List<CreateClientDto> clientListDto, BindingResult result) {

        if (result.hasErrors()) {
            return Errors.returnSintaxErrors(result);
        }

        List<Client> clientListToSave = ClientMapper.toEntityList(clientListDto);

        return ResponseEntity.ok(ClientMapper.toDtoList((List<Client>) clientRepository.saveAll(clientListToSave)));

    }
   
}
