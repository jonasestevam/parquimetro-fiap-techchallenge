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

import com.jonasestevam.parquimetro.dtos.ConductorDTO;
import com.jonasestevam.parquimetro.services.ConductorService;

@RestController
@RequestMapping("/api/conductors")
public class ConductorController {

    @Autowired
    ConductorService conductorService;

    @PostMapping
    public ResponseEntity<ConductorDTO> createConductor(@RequestBody ConductorDTO conductorDTO) {
        ConductorDTO savedConductor = conductorService.saveConductor(conductorDTO);
        return new ResponseEntity<>(savedConductor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConductorDTO>> getAllConductors() {
        List<ConductorDTO> conductors = conductorService.getAllConductors();
        return ResponseEntity.ok(conductors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConductorDTO> getConductorById(@PathVariable Long id) throws Exception {
        ConductorDTO conductor = conductorService.getConductorById(id);
        return ResponseEntity.ok(conductor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConductorDTO> updateConductor(@PathVariable Long id, @RequestBody ConductorDTO conductorDTO) {
        ConductorDTO updatedConductor = conductorService.updateConductor(id, conductorDTO);
        return ResponseEntity.ok(updatedConductor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConductor(@PathVariable Long id) {
        conductorService.deleteConductor(id);
        return ResponseEntity.noContent().build();
    }
}
