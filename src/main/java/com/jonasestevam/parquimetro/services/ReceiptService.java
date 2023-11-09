package com.jonasestevam.parquimetro.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonasestevam.parquimetro.MessageProducer;
import com.jonasestevam.parquimetro.dtos.ReceiptDTO;
import com.jonasestevam.parquimetro.mappers.ReceiptMapper;
import com.jonasestevam.parquimetro.models.ParkingSession;
import com.jonasestevam.parquimetro.models.Receipt;
import com.jonasestevam.parquimetro.repositories.ReceiptRepository;

import jakarta.persistence.NoResultException;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    ReceiptMapper mapper;

    @Autowired
    MessageProducer messageProducer;

    public void createNewReceiptAndNotify(ParkingSession session, LocalDateTime now) {
        ReceiptDTO newReceiptDTO = new ReceiptDTO();

        newReceiptDTO.setAmount(session.getPaidAmount());
        newReceiptDTO.setIssueDate(now);
        newReceiptDTO.setParkingSessionId(session.getId());

        messageProducer.notifySessionFinished(newReceiptDTO);
    }

    public ReceiptDTO saveReceipt(Receipt receipt) {
        return mapper.toDto(receiptRepository.save(receipt));
    }

    public List<ReceiptDTO> getAllReceipts() {
        return mapper.toDto(receiptRepository.findAll());
    }

    public ReceiptDTO getReceiptById(Long id) throws Exception {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        if (receipt.isPresent()) {
            return mapper.toDto(receipt.get());
        } else {
            throw new Exception("Receipt not found with id: " + id);
        }
    }

    public ReceiptDTO updateReceipt(Long id, ReceiptDTO updatedReceipt) {
        checkIfExists(id);
        updatedReceipt.setId(id);
        return mapper.toDto(receiptRepository.save(mapper.toEntity(updatedReceipt)));
    }

    private void checkIfExists(Long id) {
        if (!receiptRepository.existsById(id)) {
            throw new NoResultException("This entity was not found");
        }
    }

    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }
}
