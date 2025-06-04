package backend.car_rental.dto.client;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateClientListDto {
    
    private List<CreateClientDto> clients;
}
