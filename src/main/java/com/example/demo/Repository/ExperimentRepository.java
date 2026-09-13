package com.example.demo.Repository;

import com.example.demo.Entity.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperimentRepository
        extends JpaRepository<Experiment, Long> {
}