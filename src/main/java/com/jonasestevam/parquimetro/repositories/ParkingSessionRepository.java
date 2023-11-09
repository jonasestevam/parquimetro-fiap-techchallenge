package com.jonasestevam.parquimetro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonasestevam.parquimetro.models.ParkingSession;

@Repository
public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {
}
