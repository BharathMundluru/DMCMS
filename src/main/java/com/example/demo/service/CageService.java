package com.example.demo.service;

import com.example.demo.Entity.Cage;
import com.example.demo.Repository.CageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CageService {

    private final CageRepository cageRepository;

    public CageService(CageRepository cageRepository) {
        this.cageRepository = cageRepository;
    }

    public List<Cage> getAllCages() {
        return cageRepository.findAll();
    }

    public Cage getCageById(Long id) {
        return cageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cage not found"));
    }

    public Cage saveCage(Cage cage) {
        return cageRepository.save(cage);
    }

    public Cage updateCage(Long id, Cage updatedCage) {

        Cage cage = getCageById(id);

        cage.setCageNumber(updatedCage.getCageNumber());
        cage.setRack(updatedCage.getRack());
        cage.setRoom(updatedCage.getRoom());
        cage.setCapacity(updatedCage.getCapacity());
        cage.setOccupancy(updatedCage.getOccupancy());

        return cageRepository.save(cage);
    }

    public void deleteCage(Long id) {
        cageRepository.deleteById(id);
    }
}