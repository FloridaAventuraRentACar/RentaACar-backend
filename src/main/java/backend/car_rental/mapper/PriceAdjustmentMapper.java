package backend.car_rental.mapper;

import java.util.List;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentRequestDto;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.entities.PriceAdjustment;
import backend.car_rental.entities.PriceAdjustmentType;

public class PriceAdjustmentMapper {
    
    public static PriceAdjustment toEntity(PriceAdjustmentRequestDto priceAdjustmentRequestDto) {

        double multiplier = priceAdjustmentRequestDto.getType() == PriceAdjustmentType.INCREASE ? 
            (priceAdjustmentRequestDto.getPercentage() / 100 + 1) : 
            (1 - priceAdjustmentRequestDto.getPercentage() / 100);
        
        return PriceAdjustment.builder()
                .periodStart(priceAdjustmentRequestDto.getPeriodStart())
                .periodEnd(priceAdjustmentRequestDto.getPeriodEnd())
                .multiplier(multiplier)
                .type(priceAdjustmentRequestDto.getType())
                .reason(priceAdjustmentRequestDto.getReason())
                .build();
    }

    public static PriceAdjustment toUpdateEntity(Long id, PriceAdjustmentRequestDto priceAdjustmentRequestDto) {

        PriceAdjustment priceAdjustment = toEntity(priceAdjustmentRequestDto);
        priceAdjustment.setId(id);
        return priceAdjustment;
    }

    
    public static PriceAdjustmentResponseDto toResponseDto(PriceAdjustment priceAdjustment) {

        double percentage = priceAdjustment.getMultiplier() > 1 ? 
            (priceAdjustment.getMultiplier() - 1) * 100 : 
            (1 - priceAdjustment.getMultiplier()) * 100;

        percentage = Math.round(percentage * 100.0) / 100.0;
        
        return PriceAdjustmentResponseDto.builder()
                .id(priceAdjustment.getId())
                .periodStart(priceAdjustment.getPeriodStart())
                .periodEnd(priceAdjustment.getPeriodEnd())
                .percentage(percentage)
                .type(priceAdjustment.getType())
                .reason(priceAdjustment.getReason())
                .active(priceAdjustment.isActive())
                .build();
    }

    public static List<PriceAdjustmentResponseDto> toResponseDtoList(List<PriceAdjustment> priceAdjustments) {
        return priceAdjustments
            .stream()
            .map(PriceAdjustmentMapper::toResponseDto)
            .toList();
    }
}
