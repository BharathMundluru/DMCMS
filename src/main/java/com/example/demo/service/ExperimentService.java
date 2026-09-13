package com.example.demo.service;

import com.example.demo.Entity.Experiment;
import com.example.demo.Repository.ExperimentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperimentService {

    private final ExperimentRepository experimentRepository;

    public ExperimentService(ExperimentRepository experimentRepository) {
        this.experimentRepository = experimentRepository;
    }

    public List<Experiment> getAllExperiments() {
        return experimentRepository.findAll();
    }

    public Experiment getExperimentById(Long id) {

        return experimentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Experiment not found"));
    }

    public Experiment saveExperiment(Experiment experiment) {

        return experimentRepository.save(experiment);
    }

    public Experiment updateExperiment(
            Long id,
            Experiment updatedExperiment) {

        Experiment existing =
                getExperimentById(id);

        existing.setMouse(
                updatedExperiment.getMouse()
        );

        existing.setProjectName(
                updatedExperiment.getProjectName()
        );

        existing.setProtocolNumber(
                updatedExperiment.getProtocolNumber()
        );

        existing.setResearcher(
                updatedExperiment.getResearcher()
        );

        existing.setStartDate(
                updatedExperiment.getStartDate()
        );

        existing.setEndDate(
                updatedExperiment.getEndDate()
        );

        existing.setResult(
                updatedExperiment.getResult()
        );

        existing.setStatus(
                updatedExperiment.getStatus()
        );

        return experimentRepository.save(existing);
    }

    public void deleteExperiment(Long id) {

        experimentRepository.deleteById(id);
    }
}