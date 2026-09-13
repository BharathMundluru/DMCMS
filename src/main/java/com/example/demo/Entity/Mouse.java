package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "mice")
@Getter
@Setter
public class Mouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String mouseId;

    @Column(length = 100)
    private String strain;

    @Column(length = 20)
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(length = 50)
    private String color;

    @Column(length = 50)
    private String status;

    @Column(nullable = false)
    private Boolean genotype = false;

    @Column(unique = true, length = 100)
    private String rfid;

    @Column(unique = true, length = 100)
    private String barcode;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "colony_id")
    private Colony colony;

    @ManyToOne
    @JoinColumn(name = "cage_id")
    private Cage cage;

    @ManyToOne
    @JoinColumn(name = "mother_id")
    private Mouse mother;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private Mouse father;
}