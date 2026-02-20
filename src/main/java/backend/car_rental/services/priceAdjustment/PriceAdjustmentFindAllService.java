package backend.car_rental.services.priceAdjustment;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.mapper.PriceAdjustmentMapper;
import backend.car_rental.repositories.IPriceAdjustmentRepository;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentFindAllService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentFindAllService implements IPriceAdjustmentFindAllService {

    private final IPriceAdjustmentRepository priceAdjustmentRepository;

    @Override
    public List<PriceAdjustmentResponseDto> findAll() {
        return PriceAdjustmentMapper.toResponseDtoList(priceAdjustmentRepository.findAll());
    }
}
