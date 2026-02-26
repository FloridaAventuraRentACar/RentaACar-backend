package backend.car_rental.services.priceAdjustment;

import org.springframework.stereotype.Service;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentRequestDto;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.mapper.PriceAdjustmentMapper;
import backend.car_rental.repositories.IPriceAdjustmentRepository;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentExistsOverlappingPeriodService;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentUpdateService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentUpdateService implements IPriceAdjustmentUpdateService {

    private final IPriceAdjustmentRepository priceAdjustmentRepository;
    private final IPriceAdjustmentExistsOverlappingPeriodService priceAdjustmentExistsOverlappingPeriodService; 

    @SuppressWarnings("null")
    @Override
    public PriceAdjustmentResponseDto updatePriceAdjustment(Long id, PriceAdjustmentRequestDto priceAdjustmentRequestDto) {
        
        priceAdjustmentExistsOverlappingPeriodService.existsOverlappingPeriodExceptFromId(id, priceAdjustmentRequestDto.getPeriodStart(), priceAdjustmentRequestDto.getPeriodEnd());

        return PriceAdjustmentMapper.toResponseDto(priceAdjustmentRepository.save(PriceAdjustmentMapper.toUpdateEntity(id, priceAdjustmentRequestDto)));
    }
    
}
