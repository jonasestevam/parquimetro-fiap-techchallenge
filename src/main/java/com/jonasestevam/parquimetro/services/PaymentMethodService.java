package com.jonasestevam.parquimetro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonasestevam.parquimetro.dtos.PaymentMethodDTO;
import com.jonasestevam.parquimetro.mappers.PaymentMethodMapper;
import com.jonasestevam.parquimetro.models.PaymentMethod;
import com.jonasestevam.parquimetro.repositories.PaymentMethodRepository;

import jakarta.persistence.NoResultException;

@Service
public class PaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    PaymentMethodMapper mapper;

    public PaymentMethodDTO savePaymentMethod(PaymentMethod paymentMethod) {
        return mapper.toDto(paymentMethodRepository.save(paymentMethod));
    }

    public List<PaymentMethodDTO> getAllPaymentMethods() {
        return mapper.toDto(paymentMethodRepository.findAll());
    }

    public PaymentMethodDTO getPaymentMethodById(Long id) throws Exception {
        Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(id);
        if (paymentMethod.isPresent()) {
            return mapper.toDto(paymentMethod.get());
        } else {
            throw new Exception("PaymentMethod not found with id: " + id);
        }
    }

    public PaymentMethodDTO updatePaymentMethod(Long id, PaymentMethodDTO updatedPaymentMethod) {
        checkIfExists(id);
        updatedPaymentMethod.setId(id);
        return mapper.toDto(paymentMethodRepository.save(mapper.toEntity(updatedPaymentMethod)));
    }

    private void checkIfExists(Long id) {
        if (!paymentMethodRepository.existsById(id)) {
            throw new NoResultException("This entity was not found");
        }
    }

    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
