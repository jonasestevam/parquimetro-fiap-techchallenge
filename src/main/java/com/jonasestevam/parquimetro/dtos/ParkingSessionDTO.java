package com.jonasestevam.parquimetro.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ParkingSessionDTO {
    private Long id;
    private Long vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double paidAmount;
    private Long paymentMethodId;
}