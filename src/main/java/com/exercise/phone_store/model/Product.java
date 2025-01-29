package com.exercise.phone_store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String make;

    @Column(unique = true, nullable = false)
    private String model;

    @Column(nullable = false)
    private double price;

    @ElementCollection
    private List<String> pictures;

    @Column
    private String specifications;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    @Column
    private LocalDateTime created;

    public Product(List<String> pictures) {
        this.pictures = pictures;
    }

}
