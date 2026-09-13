package com.example.demo.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cages")
@Getter
@Setter
public class Cage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String cageNumber;

    @Column(length = 100)
    private String room;

    @Column(length = 100)
    private String rack;

    private Integer capacity;

    private Integer occupancy;
}
