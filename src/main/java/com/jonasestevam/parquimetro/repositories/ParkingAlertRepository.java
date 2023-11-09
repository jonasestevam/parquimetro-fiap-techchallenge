package com.jonasestevam.parquimetro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonasestevam.parquimetro.models.ParkingAlert;

@Repository
public interface ParkingAlertRepository extends JpaRepository<ParkingAlert, Long> {
}
