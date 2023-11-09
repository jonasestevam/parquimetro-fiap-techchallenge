package com.jonasestevam.parquimetro.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jonasestevam.parquimetro.models.OngoingParkSession;

public interface OngoingParkSessionRepository extends MongoRepository<OngoingParkSession, String> {

}
