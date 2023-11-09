package com.jonasestevam.parquimetro.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jonasestevam.parquimetro.models.OngoingParkSession;
import com.jonasestevam.parquimetro.models.ParkingSession;
import com.jonasestevam.parquimetro.repositories.OngoingParkSessionRepository;
import com.jonasestevam.parquimetro.repositories.ParkingSessionRepository;

@Service
public class OngoingParkSessionService {

    @Autowired
    OngoingParkSessionRepository ongoingParkSessionRepository;

    @Autowired
    ParkingSessionRepository parkingSessionRepository;

    @Autowired
    ReceiptService receiptService;

    @Value("${app.hourlyRate}")
    private Double hourlyRate;

    public OngoingParkSession saveOngoingParkSession(OngoingParkSession ongoingParkSession) {

        return ongoingParkSessionRepository.save(ongoingParkSession);
    }

    public List<OngoingParkSession> getAllOngoingParkSessions() {
        return ongoingParkSessionRepository.findAll();
    }

    public OngoingParkSession getOngoingParkSessionById(String id) throws Exception {
        Optional<OngoingParkSession> ongoingParkSession = ongoingParkSessionRepository.findById(id);
        if (ongoingParkSession.isPresent()) {
            return ongoingParkSession.get();
        } else {
            throw new Exception("OngoingParkSession not found with id: " + id);
        }
    }

    public void finishSession(OngoingParkSession session, LocalDateTime now) throws Exception {
        ParkingSession parkingSession = parkingSessionRepository.findById(session.getPermanentId()).get();

        if (Objects.isNull(session.getEndTime())) {
            parkingSession.setEndTime(now);
            parkingSession.setPaidAmount(session.getAmountToPay());
        }

        parkingSessionRepository.save(parkingSession);
        deleteOngoingParkSession(session.getId());

        receiptService.createNewReceiptAndNotify(parkingSession, now);

    }

    public void updateSessionAmountToPay(OngoingParkSession ongoingParkSession, LocalDateTime now) {
        var hoursPassed = Duration.between(ongoingParkSession.getStartTime(), now).toHours() + 1;
        Double newAmount = hoursPassed * hourlyRate;

        if (ongoingParkSession.getAmountToPay() != newAmount) {
            ongoingParkSession.setAmountToPay(newAmount);
            updateOngoingParkSession(ongoingParkSession);
        }

    }

    public OngoingParkSession updateOngoingParkSession(OngoingParkSession updatedOngoingParkSession) {
        return ongoingParkSessionRepository.save(updatedOngoingParkSession);
    }

    public void deleteOngoingParkSession(String id) {
        ongoingParkSessionRepository.deleteById(id);
    }

    public void markAsNotified(OngoingParkSession session) {
        session.setIsConductorNotified(true);
        updateOngoingParkSession(session);
    }

}
