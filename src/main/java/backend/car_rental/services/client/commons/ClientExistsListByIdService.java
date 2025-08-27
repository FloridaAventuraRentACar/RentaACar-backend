package backend.car_rental.services.client.commons;

import java.util.List;

import org.springframework.stereotype.Service;
import backend.car_rental.dto.client.UpdateClientDto;
import backend.car_rental.exceptions.NotFoundException;
import backend.car_rental.repositories.IClientRepository;
import backend.car_rental.services.client.interfaces.IClientExistsListByIdService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientExistsListByIdService implements IClientExistsListByIdService{

    private final IClientRepository clientRepository;

    @Override
    public void existsAll(List<UpdateClientDto> clientDtos) {

        for (UpdateClientDto clientDto : clientDtos) {
            if (!clientRepository.existsById(clientDto.getId())) {
                throw new NotFoundException("Client " + clientDto.getName() + " with id " + clientDto.getId() + " not found");
            }
        }
    }

    
}
