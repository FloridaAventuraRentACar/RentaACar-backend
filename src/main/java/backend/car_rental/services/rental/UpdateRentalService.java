package backend.car_rental.services.rental;

import java.util.List;
import org.springframework.stereotype.Service;
import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.dto.rental.UpdateRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.Rental;
import backend.car_rental.exceptions.NotFoundException;
import backend.car_rental.mapper.ClientMapper;
import backend.car_rental.mapper.RentalMapper;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.car.interfaces.ICarFindByIdService;
import backend.car_rental.services.client.interfaces.IClientExistsListByIdService;
import backend.car_rental.services.rental.interfaces.IRentalCheckAvaibilityService;
import backend.car_rental.services.rental.interfaces.IUpdateRentalService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateRentalService implements IUpdateRentalService {
    
    private final IRentalRepository rentalRepository;
    private final IRentalCheckAvaibilityService rentalCheckAvaibilityService;
    private final IClientExistsListByIdService clientExistsListByIdService;
    private final ICarFindByIdService carFindByIdService;

    @Override
    public ResponseRentalDto update(UpdateRentalDto rentalDto, Long id) {

        if (!rentalRepository.existsById(id)) {
            throw new NotFoundException("Rental with id " + id + " not found");
        }

        Car car = carFindByIdService.findCarById(rentalDto.getCarId());

        rentalCheckAvaibilityService.isAvailableExceptId( id, rentalDto.getCarId() , rentalDto.getStart(), rentalDto.getEnd());   

        clientExistsListByIdService.existsAll(rentalDto.getClients());
        
        List<Client> clientsToSave = ClientMapper.toUpdateModelList(rentalDto.getClients());

        Rental rentalToSave = RentalMapper.toUpdateModel(id, rentalDto, car, clientsToSave);

        rentalToSave.calculateDaysRented();

        return RentalMapper.toDto(rentalRepository.save(rentalToSave));
    }
    
}
