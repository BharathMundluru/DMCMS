package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "breeding_records")
@Getter
@Setter
public class Breeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "male_mouse_id")
    private Mouse maleMouse;

    @ManyToOne(optional = false)
    @JoinColumn(name = "female_mouse_id")
    private Mouse femaleMouse;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate matingDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Integer litterSize;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate weaningDate;

    @Column(length = 50)
    private String status;
}