package com.example.demo.service;

import com.example.demo.Entity.Colony;
import com.example.demo.Repository.ColonyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColonyService {

    private final ColonyRepository colonyRepository;

    public ColonyService(ColonyRepository colonyRepository) {
        this.colonyRepository = colonyRepository;
    }

    public List<Colony> getAllColonies() {
        return colonyRepository.findAll();
    }

    public Colony getColonyById(Long id) {

        return colonyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Colony not found"));
    }

    public Colony saveColony(Colony colony) {

        return colonyRepository.save(colony);
    }

    public void deleteColony(Long id) {

        colonyRepository.deleteById(id);
    }
}