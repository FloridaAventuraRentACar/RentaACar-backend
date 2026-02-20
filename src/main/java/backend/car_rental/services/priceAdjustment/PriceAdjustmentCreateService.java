package backend.car_rental.services.priceAdjustment;

import org.springframework.stereotype.Service;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentRequestDto;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.mapper.PriceAdjustmentMapper;
import backend.car_rental.repositories.IPriceAdjustmentRepository;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentCreateService;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentExistsOverlappingPeriodService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentCreateService implements IPriceAdjustmentCreateService {

    private final IPriceAdjustmentRepository priceAdjustmentRepository;
    private final IPriceAdjustmentExistsOverlappingPeriodService priceAdjustmentExistsOverlappingPeriodService;
    
    @SuppressWarnings("null")
    @Override
    public PriceAdjustmentResponseDto createPriceAdjustment(PriceAdjustmentRequestDto priceAdjustmentRequestDto) {
        
        priceAdjustmentExistsOverlappingPeriodService.existsOverlappingPeriod(priceAdjustmentRequestDto.getPeriodStart(), priceAdjustmentRequestDto.getPeriodEnd());

        return PriceAdjustmentMapper.toResponseDto(priceAdjustmentRepository.save(PriceAdjustmentMapper.toEntity(priceAdjustmentRequestDto)));
    }
}
