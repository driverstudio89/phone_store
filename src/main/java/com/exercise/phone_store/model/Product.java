package com.exercise.phone_store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    private List<String> pictures;

    @Column
    private String specifications;

    public Product(List<String> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Product{\n" +
                "id=" + id +
                ",\n make='" + make + '\'' +
                ",\n model='" + model + '\'' +
                ",\n price=" + price +
                ",\n pictures=" + pictures +
                ",\n specifications='" + specifications + '\'' +
                '}';
    }
}
