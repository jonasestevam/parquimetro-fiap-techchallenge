
package com.jonasestevam.parquimetro.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jonasestevam.parquimetro.dtos.ParkingAlertDTO;
import com.jonasestevam.parquimetro.models.ParkingAlert;

@Mapper(componentModel = "spring")
public interface ParkingAlertMapper extends BaseMapper<ParkingAlertDTO, ParkingAlert> {
    ParkingAlertMapper INSTANCE = Mappers.getMapper(ParkingAlertMapper.class);
}
