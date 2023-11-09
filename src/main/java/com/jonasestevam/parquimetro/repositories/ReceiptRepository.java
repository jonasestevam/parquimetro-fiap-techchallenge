package com.jonasestevam.parquimetro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonasestevam.parquimetro.models.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
