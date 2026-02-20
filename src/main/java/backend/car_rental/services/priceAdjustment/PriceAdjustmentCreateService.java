package backend.car_rental.services.priceAdjustment;

import org.springframework.stereotype.Service;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentRequestDto;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.exceptions.ConflictException;
import backend.car_rental.mapper.PriceAdjustmentMapper;
import backend.car_rental.repositories.IPriceAdjustmentRepository;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentCreateService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentCreateService implements IPriceAdjustmentCreateService {

    private final IPriceAdjustmentRepository priceAdjustmentRepository;

    @SuppressWarnings("null")
    @Override
    public PriceAdjustmentResponseDto createPriceAdjustment(PriceAdjustmentRequestDto priceAdjustmentRequestDto) {
        
        if(priceAdjustmentRepository.existsOverlappingPeriod(priceAdjustmentRequestDto.getPeriodStart(), priceAdjustmentRequestDto.getPeriodEnd())) {
            throw new ConflictException("Ya existe un ajuste de precios que se solapa con el periodo seleccionado");
        }

        return PriceAdjustmentMapper.toResponseDto(priceAdjustmentRepository.save(PriceAdjustmentMapper.toEntity(priceAdjustmentRequestDto)));
    }
}
