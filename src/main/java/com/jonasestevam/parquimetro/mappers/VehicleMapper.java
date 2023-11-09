package com.jonasestevam.parquimetro.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jonasestevam.parquimetro.dtos.VehicleDTO;
import com.jonasestevam.parquimetro.models.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper extends BaseMapper<VehicleDTO, Vehicle> {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(target = "conductor.id", source = "conductorId")
    @Override
    Vehicle toEntity(VehicleDTO vehicleDTO);

    @Mapping(target = "conductorId", source = "conductor.id")
    @Override
    VehicleDTO toDto(Vehicle vehicle);

    @Mapping(target = "conductorId", source = "conductor.id")
    @Override
    List<VehicleDTO> toDto(List<Vehicle> entity);

}
