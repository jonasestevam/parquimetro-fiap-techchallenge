package com.jonasestevam.parquimetro.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ParkingAlertDTO {
    private Long id;
    private Long conductorId;
    private LocalDateTime alertTime;
    private String message;
}