package com.jonasestevam.parquimetro.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jonasestevam.parquimetro.dtos.ConductorDTO;
import com.jonasestevam.parquimetro.models.Conductor;

@Mapper(componentModel = "spring")
public interface ConductorMapper extends BaseMapper<ConductorDTO, Conductor> {
    ConductorMapper INSTANCE = Mappers.getMapper(ConductorMapper.class);
}
