package com.example.demo.Repository;

import com.example.demo.Entity.Breeding;
import com.example.demo.Entity.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedingRepository extends JpaRepository<Breeding, Long> {
}