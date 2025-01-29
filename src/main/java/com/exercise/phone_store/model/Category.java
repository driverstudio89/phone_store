package com.exercise.phone_store.model;

import com.exercise.phone_store.model.enums.CategoryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @OneToMany(targetEntity = Product.class, mappedBy = "category")
    private List<Product> products;

    public Category(CategoryType categoryType) {
        this.category = categoryType;
    }
}