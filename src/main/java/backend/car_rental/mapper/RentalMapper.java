package backend.car_rental.mapper;

import java.util.List;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.dto.rental.CurrentRentalsResponseDto;
import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.dto.rental.UpdateRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.Rental;

public class RentalMapper {
    
    public static Rental toEntity(CreateRentalDto rentalDto, Car car,   List<Client> clients) {
        return Rental.builder()
            .car(car)
            .clients(clients)
            .start(rentalDto.getStart())
            .end(rentalDto.getEnd())
            .pickupLocation(rentalDto.getPickupLocation())
            .returnLocation(rentalDto.getReturnLocation())
            .insurance(rentalDto.getInsurance())
            .babySeat(rentalDto.getBabySeat())
            .travelLocation(rentalDto.getTravelLocation())
            .gasTank(rentalDto.getGasTank())
            .build();
    }    

    public static ResponseRentalDto toDto(Rental rental) {
        return ResponseRentalDto.builder()
            .id(rental.getId())
            .start(rental.getStart())
            .end(rental.getEnd())
            .pickupLocation(rental.getPickupLocation())
            .returnLocation(rental.getReturnLocation())
            .carName(rental.getCar().getBrand() + " " + rental.getCar().getModel())
            .clients(ClientMapper.toDtoList(rental.getClients()))
            .totalPrice(rental.getTotalPrice())
            .daysRented(rental.getDaysRented())
            .insurance(rental.getInsurance())
            .babySeat(rental.getBabySeat())
            .travelLocation(rental.getTravelLocation())
            .gasTank(rental.getGasTank())
            .build();
    }

    public static CurrentRentalsResponseDto toCurrentRentalDto(Rental rental) {
        return CurrentRentalsResponseDto.builder()
            .id(rental.getId())
            .carId(rental.getCar().getId()) 
            .carName(rental.getCar().getBrand() + " " + rental.getCar().getModel())
            .clientName(rental.getClients().get(0).getName() + " " + rental.getClients().get(0).getSurname())
            .start(rental.getStart().toLocalDate())
            .end(rental.getEnd().toLocalDate())
            .totalPrice(rental.getTotalPrice())
            .build();
    }

    public static List<CurrentRentalsResponseDto> toCurrentRentalDtoList(List<Rental> rentals) {
        return rentals
            .stream()
            .map(RentalMapper::toCurrentRentalDto)
            .toList();
    }

    public static Rental toUpdateModel(Long id, UpdateRentalDto rentalDto, Car car, List<Client> clients) {

        return Rental.builder()
            .id(id)
            .car(car)
            .clients(clients)
            .start(rentalDto.getStart())
            .end(rentalDto.getEnd())
            .pickupLocation(rentalDto.getPickupLocation())
            .returnLocation(rentalDto.getReturnLocation())
            .insurance(rentalDto.getInsurance())
            .babySeat(rentalDto.getBabySeat())
            .travelLocation(rentalDto.getTravelLocation())
            .gasTank(rentalDto.getGasTank())
            .build();
    }

}
