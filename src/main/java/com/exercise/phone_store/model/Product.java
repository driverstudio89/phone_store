package com.exercise.phone_store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String make;

    @Column(unique = true, nullable = false)
    private String model;

    @Column(nullable = false)
    private double price;

    @Column
    private String pictures;

    @Column
    private String specifications;
}
