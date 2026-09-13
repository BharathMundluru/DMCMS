package com.example.demo.Repository;


import com.example.demo.Entity.Cage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CageRepository extends JpaRepository<Cage, Long> {
}