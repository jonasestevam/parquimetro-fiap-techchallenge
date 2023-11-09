package com.jonasestevam.parquimetro.models;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document
public class OngoingParkSession {
    @Id
    private String id;
    private Long permanentId;
    private Long vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double amountToPay;
    private Boolean isConductorNotified;
}
