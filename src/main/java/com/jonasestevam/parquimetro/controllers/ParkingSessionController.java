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

import com.jonasestevam.parquimetro.dtos.ParkingSessionDTO;
import com.jonasestevam.parquimetro.services.ParkingSessionService;

@RestController
@RequestMapping("/api/parksessions")
public class ParkingSessionController {

    @Autowired
    ParkingSessionService parkSessionService;

    @PostMapping("/start")
    public ResponseEntity<ParkingSessionDTO> createParkingSession(@RequestBody ParkingSessionDTO parkSessionDTO) {
        ParkingSessionDTO savedParkingSession = parkSessionService
                .saveParkingSession(parkSessionDTO);
        return new ResponseEntity<>(savedParkingSession, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSessionDTO>> getAllParkingSessions() {
        List<ParkingSessionDTO> parkSessions = parkSessionService.getAllParkingSessions();
        return ResponseEntity.ok(parkSessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSessionDTO> getParkingSessionById(@PathVariable Long id) throws Exception {
        ParkingSessionDTO parkSession = parkSessionService.getParkingSessionById(id);
        return ResponseEntity.ok(parkSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSessionDTO> updateParkingSession(@PathVariable Long id,
            @RequestBody ParkingSessionDTO parkSessionDTO) {
        ParkingSessionDTO updatedParkingSession = parkSessionService.updateParkingSession(id, parkSessionDTO);
        return ResponseEntity.ok(updatedParkingSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSession(@PathVariable Long id) {
        parkSessionService.deleteParkingSession(id);
        return ResponseEntity.noContent().build();
    }
}
