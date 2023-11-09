package com.jonasestevam.parquimetro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonasestevam.parquimetro.dtos.ParkingSessionDTO;
import com.jonasestevam.parquimetro.mappers.ParkingSessionMapper;
import com.jonasestevam.parquimetro.models.OngoingParkSession;
import com.jonasestevam.parquimetro.models.ParkingSession;
import com.jonasestevam.parquimetro.repositories.ParkingSessionRepository;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ParkingSessionService {

    @Autowired
    ParkingSessionRepository parkingSessionRepository;

    @Autowired
    OngoingParkSessionService ongoingParkSessionService;

    @Autowired
    ParkingSessionMapper mapper;

    public ParkingSessionDTO saveParkingSession(ParkingSessionDTO parkingSessionDTO) {

        ParkingSession savedSession = parkingSessionRepository.save(mapper.toEntity(parkingSessionDTO));

        OngoingParkSession ongoingSession = new OngoingParkSession();

        ongoingSession.setPermanentId(savedSession.getId());
        ongoingSession.setStartTime(savedSession.getStartTime());
        ongoingSession.setVehicleId(savedSession.getVehicle().getId());
        ongoingSession.setEndTime(savedSession.getEndTime());
        ongoingSession.setAmountToPay(savedSession.getPaidAmount());
        ongoingSession.setIsConductorNotified(false);

        ongoingParkSessionService.saveOngoingParkSession(ongoingSession);

        return mapper.toDto(savedSession);
    }

    public List<ParkingSessionDTO> getAllParkingSessions() {
        return mapper.toDto(parkingSessionRepository.findAll());
    }

    public ParkingSessionDTO getParkingSessionById(Long id) throws Exception {
        Optional<ParkingSession> parkingSession = parkingSessionRepository.findById(id);
        if (parkingSession.isPresent()) {
            return mapper.toDto(parkingSession.get());
        } else {
            throw new Exception("ParkingSession not found with id: " + id);
        }
    }

    public ParkingSessionDTO updateParkingSession(Long id, ParkingSessionDTO updatedParkingSession) {
        checkIfExists(id);
        updatedParkingSession.setId(id);
        return mapper.toDto(parkingSessionRepository.save(mapper.toEntity(updatedParkingSession)));
    }

    private void checkIfExists(Long id) {
        if (!parkingSessionRepository.existsById(id)) {
            throw new NoResultException("This entity was not found");
        }
    }

    public void deleteParkingSession(Long id) {
        parkingSessionRepository.deleteById(id);
    }
}
