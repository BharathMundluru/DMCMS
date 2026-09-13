package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "health_records")
@Getter
@Setter
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mouse_id")
    private Mouse mouse;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @Column(length = 100)
    private String condition;

    @Column(length = 1000)
    private String symptoms;

    @Column(length = 1000)
    private String diagnosis;

    @Column(length = 1000)
    private String treatment;

    @Column(length = 100)
    private String veterinarian;

    @Column(length = 50)
    private String status;

    @Column(length = 1000)
    private String notes;
}