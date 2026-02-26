package backend.car_rental.services.priceAdjustment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
    List<PriceAdjustmentResponseDto> priceAdjustments = new ArrayList<>(PriceAdjustmentMapper.toResponseDtoList(priceAdjustmentRepository.findAll()));
    
    priceAdjustments.forEach(p -> {
        if (p.getPeriodEnd().isBefore(LocalDate.now())) {
            p.setActive(false);
        }
    });

    // Ordenamiento por grupos (Activos primero) y luego por fecha de inicio
    priceAdjustments.sort(Comparator
            .comparing(PriceAdjustmentResponseDto::isActive, Comparator.reverseOrder())
            .thenComparing(PriceAdjustmentResponseDto::getPeriodStart)
    );

    return priceAdjustments;
}
}
