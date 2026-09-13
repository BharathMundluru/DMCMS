package com.example.demo.Repository;


import com.example.demo.Entity.Colony;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColonyRepository extends JpaRepository<Colony, Long> {
}
