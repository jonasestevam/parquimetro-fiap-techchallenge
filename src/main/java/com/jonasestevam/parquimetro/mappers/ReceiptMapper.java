package com.jonasestevam.parquimetro.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jonasestevam.parquimetro.dtos.ReceiptDTO;
import com.jonasestevam.parquimetro.models.Receipt;

@Mapper(componentModel = "spring")
public interface ReceiptMapper extends BaseMapper<ReceiptDTO, Receipt> {
    ReceiptMapper INSTANCE = Mappers.getMapper(ReceiptMapper.class);
}
