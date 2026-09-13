package com.example.demo.Repository;

import com.example.demo.Entity.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MouseRepository extends JpaRepository<Mouse, Long> {

    Optional<Mouse> findByMouseId(String mouseId);

    List<Mouse> findByBarcodeContainingIgnoreCase(String barcode);

    List<Mouse> findByStrainContainingIgnoreCase(String strain);

    List<Mouse> findByMother(Mouse mother);

    List<Mouse> findByFather(Mouse father);

    List<Mouse> findByGender(String gender);
}