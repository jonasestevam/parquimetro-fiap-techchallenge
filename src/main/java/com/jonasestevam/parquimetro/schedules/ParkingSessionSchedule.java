package com.jonasestevam.parquimetro.schedules;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jonasestevam.parquimetro.MessageProducer;
import com.jonasestevam.parquimetro.models.OngoingParkSession;
import com.jonasestevam.parquimetro.services.OngoingParkSessionService;

@Component
public class ParkingSessionSchedule {

    @Autowired
    OngoingParkSessionService ongoingParkSessionService;

    @Autowired
    MessageProducer messageProducer;

    @Scheduled(fixedRate = 1000)
    public void checkOngoingParkingSessions() {
        List<OngoingParkSession> ongoingParkSession = ongoingParkSessionService.getAllOngoingParkSessions();
        LocalDateTime now = LocalDateTime.now();

        ongoingParkSession.stream().forEach(session -> {
            if (Objects.isNull(session.getEndTime())) {
                ongoingParkSessionService.updateSessionAmountToPay(session, now);
            } else {
                if (session.getEndTime().isBefore(now) || session.getEndTime().isEqual(now)) {
                    try {
                        ongoingParkSessionService.finishSession(session, now);
                    } catch (Exception e) {
                    }
                } else if (Duration.between(now, session.getEndTime()).toMinutes() <= 10
                        && Boolean.FALSE.equals(session.getIsConductorNotified())) {
                    ongoingParkSessionService.markAsNotified(session);
                    if (Objects.isNull(session.getEndTime())) {
                        messageProducer.notifySessionAlmostEndingVariableTime(session);
                    } else {
                        messageProducer.notifySessionAlmostEnding(session);
                    }
                }
            }

        });

    }
}
