package com.jonasestevam.parquimetro.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jonasestevam.parquimetro.dtos.ParkingSessionDTO;
import com.jonasestevam.parquimetro.models.ParkingSession;

@Mapper(componentModel = "spring")
public interface ParkingSessionMapper extends BaseMapper<ParkingSessionDTO, ParkingSession> {
    ParkingSessionMapper INSTANCE = Mappers.getMapper(ParkingSessionMapper.class);

    @Mapping(target = "vehicle.id", source = "vehicleId")
    @Mapping(target = "paymentMethod.id", source = "paymentMethodId")
    @Override
    ParkingSession toEntity(ParkingSessionDTO vehicleDTO);

    @Mapping(target = "vehicleId", source = "vehicle.id")
    @Mapping(target = "paymentMethodId", source = "paymentMethod.id")
    @Override
    ParkingSessionDTO toDto(ParkingSession vehicle);

    @Mapping(target = "vehicleId", source = "vehicle.id")
    @Mapping(target = "paymentMethodId", source = "paymentMethod.id")
    @Override
    List<ParkingSessionDTO> toDto(List<ParkingSession> entity);

}
