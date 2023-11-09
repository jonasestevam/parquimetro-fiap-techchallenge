package com.jonasestevam.parquimetro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonasestevam.parquimetro.dtos.ParkingAlertDTO;
import com.jonasestevam.parquimetro.mappers.ParkingAlertMapper;
import com.jonasestevam.parquimetro.models.ParkingAlert;
import com.jonasestevam.parquimetro.repositories.ParkingAlertRepository;

import jakarta.persistence.NoResultException;

@Service
public class ParkingAlertService {

    @Autowired
    ParkingAlertRepository parkingAlertRepository;

    @Autowired
    ParkingAlertMapper mapper;

    public ParkingAlertDTO saveParkingAlert(ParkingAlert parkingAlert) {
        return mapper.toDto(parkingAlertRepository.save(parkingAlert));
    }

    public List<ParkingAlertDTO> getAllParkingAlerts() {
        return mapper.toDto(parkingAlertRepository.findAll());
    }

    public ParkingAlertDTO getParkingAlertById(Long id) throws Exception {
        Optional<ParkingAlert> parkingAlert = parkingAlertRepository.findById(id);
        if (parkingAlert.isPresent()) {
            return mapper.toDto(parkingAlert.get());
        } else {
            throw new Exception("ParkingAlert not found with id: " + id);
        }
    }

    public ParkingAlertDTO updateParkingAlert(Long id, ParkingAlertDTO updatedParkingAlert) {
        checkIfExists(id);
        updatedParkingAlert.setId(id);
        return mapper.toDto(parkingAlertRepository.save(mapper.toEntity(updatedParkingAlert)));
    }

    private void checkIfExists(Long id) {
        if (!parkingAlertRepository.existsById(id)) {
            throw new NoResultException("This entity was not found");
        }
    }

    public void deleteParkingAlert(Long id) {
        parkingAlertRepository.deleteById(id);
    }
}
