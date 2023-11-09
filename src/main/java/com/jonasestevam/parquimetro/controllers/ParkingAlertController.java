package com.jonasestevam.parquimetro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonasestevam.parquimetro.dtos.ParkingAlertDTO;
import com.jonasestevam.parquimetro.mappers.ParkingAlertMapper;
import com.jonasestevam.parquimetro.models.ParkingAlert;
import com.jonasestevam.parquimetro.services.ParkingAlertService;

@RestController
@RequestMapping("/api/parkingAlerts")
public class ParkingAlertController {

    private final ParkingAlertService parkingAlertService;
    private final ParkingAlertMapper parkingAlertMapper;

    @Autowired
    public ParkingAlertController(ParkingAlertService parkingAlertService, ParkingAlertMapper parkingAlertMapper) {
        this.parkingAlertService = parkingAlertService;
        this.parkingAlertMapper = parkingAlertMapper;
    }

    @PostMapping
    public ResponseEntity<ParkingAlertDTO> createParkingAlert(@RequestBody ParkingAlertDTO parkingAlertDTO) {
        ParkingAlert parkingAlert = parkingAlertMapper.toEntity(parkingAlertDTO);
        ParkingAlertDTO savedParkingAlert = parkingAlertService.saveParkingAlert(parkingAlert);
        return new ResponseEntity<>(savedParkingAlert, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParkingAlertDTO>> getAllParkingAlerts() {
        List<ParkingAlertDTO> parkingAlerts = parkingAlertService.getAllParkingAlerts();
        return ResponseEntity.ok(parkingAlerts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingAlertDTO> getParkingAlertById(@PathVariable Long id) throws Exception {
        ParkingAlertDTO parkingAlert = parkingAlertService.getParkingAlertById(id);
        return ResponseEntity.ok(parkingAlert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingAlertDTO> updateParkingAlert(@PathVariable Long id,
            @RequestBody ParkingAlertDTO parkingAlertDTO) {
        ParkingAlertDTO updatedParkingAlert = parkingAlertService.updateParkingAlert(id, parkingAlertDTO);
        return ResponseEntity.ok(updatedParkingAlert);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingAlert(@PathVariable Long id) {
        parkingAlertService.deleteParkingAlert(id);
        return ResponseEntity.noContent().build();
    }
}
