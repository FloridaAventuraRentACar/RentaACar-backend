package backend.car_rental.mapper;

import java.util.List;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentRequestDto;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.entities.PriceAdjustment;

public class PriceAdjustmentMapper {
    
    public static PriceAdjustment toEntity(PriceAdjustmentRequestDto priceAdjustmentRequestDto) {
        return PriceAdjustment.builder()
                .periodStart(priceAdjustmentRequestDto.getPeriodStart())
                .periodEnd(priceAdjustmentRequestDto.getPeriodEnd())
                .multiplier(priceAdjustmentRequestDto.getMultiplier())
                .reason(priceAdjustmentRequestDto.getReason())
                .build();
    }

    public static PriceAdjustmentResponseDto toResponseDto(PriceAdjustment priceAdjustment) {
        return PriceAdjustmentResponseDto.builder()
                .id(priceAdjustment.getId())
                .periodStart(priceAdjustment.getPeriodStart())
                .periodEnd(priceAdjustment.getPeriodEnd())
                .multiplier(priceAdjustment.getMultiplier())
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
