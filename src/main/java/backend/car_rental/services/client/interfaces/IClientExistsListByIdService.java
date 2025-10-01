package backend.car_rental.services.client.interfaces;

import java.util.List;

import backend.car_rental.dto.client.UpdateClientDto;

public interface IClientExistsListByIdService {
    
    void existsAll(List<UpdateClientDto> clientDtos);
}
