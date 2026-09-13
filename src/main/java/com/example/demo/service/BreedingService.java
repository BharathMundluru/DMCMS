package com.example.demo.service;

import com.example.demo.Entity.Breeding;
import com.example.demo.Repository.BreedingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedingService {

    private final BreedingRepository breedingRepository;

    public BreedingService(BreedingRepository breedingRepository) {
        this.breedingRepository = breedingRepository;
    }

    public List<Breeding> getAllBreedings() {
        return breedingRepository.findAll();
    }

    public Breeding getBreedingById(Long id) {
        return breedingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Breeding record not found"));
    }

    public Breeding saveBreeding(Breeding breeding) {
        return breedingRepository.save(breeding);
    }

    public Breeding updateBreeding(Long id, Breeding breedingDetails) {

        Breeding breeding = getBreedingById(id);

        breeding.setMaleMouse(breedingDetails.getMaleMouse());
        breeding.setFemaleMouse(breedingDetails.getFemaleMouse());
        breeding.setMatingDate(breedingDetails.getMatingDate());
        breeding.setBirthDate(breedingDetails.getBirthDate());
        breeding.setLitterSize(breedingDetails.getLitterSize());
        breeding.setWeaningDate(breedingDetails.getWeaningDate());
        breeding.setStatus(breedingDetails.getStatus());

        return breedingRepository.save(breeding);
    }

    public void deleteBreeding(Long id) {
        breedingRepository.deleteById(id);
    }
}