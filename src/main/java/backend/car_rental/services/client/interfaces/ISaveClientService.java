package backend.car_rental.services.client.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.client.CreateClientDto;

public interface ISaveClientService {

    ResponseEntity<?> save(CreateClientDto client, BindingResult result);

    ResponseEntity<?> saveAll(List<CreateClientDto> clientListDto, BindingResult result);
}
