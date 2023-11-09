package com.jonasestevam.parquimetro.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jonasestevam.parquimetro.dtos.PaymentMethodDTO;
import com.jonasestevam.parquimetro.models.PaymentMethod;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper extends BaseMapper<PaymentMethodDTO, PaymentMethod> {
    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    @Mapping(target = "conductor.id", source = "conductorId")
    @Override
    PaymentMethod toEntity(PaymentMethodDTO vehicleDTO);

    @Mapping(target = "conductorId", source = "conductor.id")
    @Override
    PaymentMethodDTO toDto(PaymentMethod vehicle);

    @Mapping(target = "conductorId", source = "conductor.id")
    @Override
    List<PaymentMethodDTO> toDto(List<PaymentMethod> entity);
}
