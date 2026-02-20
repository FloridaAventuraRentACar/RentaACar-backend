package backend.car_rental.services.car.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import backend.car_rental.dto.car.ResponseCarDto;

public interface ICarGetAvailableService {
    List<ResponseCarDto> getAvailableCars(LocalDateTime startDateTime , LocalDateTime endDateTime);
}
