package com.jonasestevam.parquimetro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonasestevam.parquimetro.dtos.VehicleDTO;
import com.jonasestevam.parquimetro.mappers.VehicleMapper;
import com.jonasestevam.parquimetro.models.Vehicle;
import com.jonasestevam.parquimetro.repositories.VehicleRepository;

import jakarta.persistence.NoResultException;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleMapper mapper;

    public VehicleDTO saveVehicle(VehicleDTO vehicle) {
        return mapper.toDto(vehicleRepository.save(mapper.toEntity(vehicle)));
    }

    public List<VehicleDTO> getAllVehicles() {
        return mapper.toDto(vehicleRepository.findAll());
    }

    public VehicleDTO getVehicleById(Long id) throws Exception {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return mapper.toDto(vehicle.get());
        } else {
            throw new Exception("Vehicle not found with id: " + id);
        }
    }

    public VehicleDTO updateVehicle(Long id, VehicleDTO updatedVehicle) {
        checkIfExists(id);
        updatedVehicle.setId(id);
        return mapper.toDto(vehicleRepository.save(mapper.toEntity(updatedVehicle)));
    }

    private void checkIfExists(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new NoResultException("This entity was not found");
        }
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
