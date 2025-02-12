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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String make;

    @Column(unique = true, nullable = false)
    private String model;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @ElementCollection
    private List<String> pictures;

    @Column
    private String specifications;

    @ManyToOne(targetEntity = Category.class)
    private Category category;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    public Product() {
    }

    public Product(List<String> pictures) {
        this.pictures = pictures;
    }

    public Category getCategory() {
        return category;
    }

    public Product setCategory(Category category) {
        this.category = category;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Product setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public Product setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getMake() {
        return make;
    }

    public Product setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Product setModel(String model) {
        this.model = model;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public Product setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getSpecifications() {
        return specifications;
    }

    public Product setSpecifications(String specifications) {
        this.specifications = specifications;
        return this;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public Product setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }
}
