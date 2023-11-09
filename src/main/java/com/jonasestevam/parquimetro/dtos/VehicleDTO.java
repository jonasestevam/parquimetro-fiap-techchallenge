package com.jonasestevam.parquimetro.dtos;

import lombok.Data;

@Data
public class VehicleDTO {
    private Long id;
    private String plateNumber;
    private String model;
    private Long conductorId;
    private String color;
    private Integer year;
}