package com.jonasestevam.parquimetro.dtos;

import lombok.Data;

@Data
public class ConductorDTO {
    private Long id;
    private String name;
    private String address;
    private String email;
}