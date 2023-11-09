package com.jonasestevam.parquimetro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonasestevam.parquimetro.dtos.ConductorDTO;
import com.jonasestevam.parquimetro.mappers.ConductorMapper;
import com.jonasestevam.parquimetro.models.Conductor;
import com.jonasestevam.parquimetro.repositories.ConductorRepository;

import jakarta.persistence.NoResultException;

@Service
public class ConductorService {

    @Autowired
    ConductorRepository conductorRepository;

    @Autowired
    ConductorMapper mapper;

    public ConductorDTO saveConductor(ConductorDTO conductor) {
        return mapper.toDto(conductorRepository.save(mapper.toEntity(conductor)));
    }

    public List<ConductorDTO> getAllConductors() {
        return mapper.toDto(conductorRepository.findAll());
    }

    public ConductorDTO getConductorById(Long id) throws Exception {
        Optional<Conductor> conductor = conductorRepository.findById(id);
        if (conductor.isPresent()) {
            return mapper.toDto(conductor.get());
        } else {
            throw new Exception("Conductor not found with id: " + id);
        }
    }

    public ConductorDTO updateConductor(Long id, ConductorDTO updatedConductor) {
        checkIfExists(id);
        updatedConductor.setId(id);
        return mapper.toDto(conductorRepository.save(mapper.toEntity(updatedConductor)));
    }

    private void checkIfExists(Long id) {
        if (!conductorRepository.existsById(id)) {
            throw new NoResultException("This entity was not found");
        }
    }

    public void deleteConductor(Long id) {
        conductorRepository.deleteById(id);
    }

}
