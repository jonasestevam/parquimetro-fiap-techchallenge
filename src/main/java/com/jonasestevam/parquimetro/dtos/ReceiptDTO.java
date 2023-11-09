package com.jonasestevam.parquimetro.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReceiptDTO {
    private Long id;
    private Long parkingSessionId;
    private LocalDateTime issueDate;
    private Double amount;
}