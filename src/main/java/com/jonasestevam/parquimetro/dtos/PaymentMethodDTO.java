package com.jonasestevam.parquimetro.dtos;

import lombok.Data;

@Data
public class PaymentMethodDTO {
    private Long id;
    private Long conductorId;
    private String type;
    private String details;
}