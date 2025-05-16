package backend.car_rental.mapper;


import java.util.List;

import backend.car_rental.dto.client.CreateClientDto;
import backend.car_rental.dto.client.ResponseClientDto;
import backend.car_rental.entities.Client;

public class ClientMapper {
    public static Client toEntity(CreateClientDto clientDto){
        return Client.builder()
            .name(clientDto.getName())
            .surname(clientDto.getSurname())
            .email(clientDto.getEmail())
            .phone(clientDto.getPhone())
            .licenseName(clientDto.getLicenseName())
            .licenseNumber(clientDto.getLicenseNumber())
            .licenseAddress(clientDto.getLicenseAddress())
            .bornDate(clientDto.getBornDate())
            .licenseExpirationDate(clientDto.getLicenseExpirationDate())
            .mainDriver(clientDto.getMainDriver())
            .build();
    }

    public static ResponseClientDto tDto(Client client){
        return ResponseClientDto.builder()
            .id(client.getId())
            .name(client.getName())
            .surname(client.getSurname())
            .email(client.getEmail())
            .phone(client.getPhone())
            .licenseNumber(client.getLicenseNumber())
            .bornDate(client.getBornDate())
            .licenseName(client.getLicenseName())
            .licenseAddress(client.getLicenseAddress())
            .licenseExpirationDate(client.getLicenseExpirationDate())
            .mainDriver(client.getMainDriver())
            .build();
    }

    public static List<ResponseClientDto> toDtoList(List<Client> clients){
        return clients.stream()
            .map(c -> tDto(c))
            .toList();
    }
}
