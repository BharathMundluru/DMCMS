package com.example.demo.service;

import com.example.demo.Entity.HealthRecord;
import com.example.demo.Repository.HealthRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthRecordService {

    private final HealthRecordRepository healthRecordRepository;

    public HealthRecordService(
            HealthRecordRepository healthRecordRepository) {

        this.healthRecordRepository = healthRecordRepository;
    }

    public List<HealthRecord> getAllHealthRecords() {

        return healthRecordRepository.findAll();
    }

    public HealthRecord getHealthRecordById(Long id) {

        return healthRecordRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Health record not found"));
    }

    public HealthRecord saveHealthRecord(
            HealthRecord healthRecord) {

        return healthRecordRepository.save(healthRecord);
    }

    public HealthRecord updateHealthRecord(
            Long id,
            HealthRecord updatedRecord) {

        HealthRecord existing =
                healthRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Health record not found"));

        existing.setMouse(updatedRecord.getMouse());
        existing.setRecordDate(updatedRecord.getRecordDate());
        existing.setCondition(updatedRecord.getCondition());
        existing.setSymptoms(updatedRecord.getSymptoms());
        existing.setDiagnosis(updatedRecord.getDiagnosis());
        existing.setTreatment(updatedRecord.getTreatment());
        existing.setVeterinarian(updatedRecord.getVeterinarian());
        existing.setStatus(updatedRecord.getStatus());
        existing.setNotes(updatedRecord.getNotes());

        return healthRecordRepository.save(existing);
    }

    public void deleteHealthRecord(Long id) {

        healthRecordRepository.deleteById(id);
    }
}