package com.exercise.phone_store.data;

import com.exercise.phone_store.model.Product;
import com.exercise.phone_store.web.dto.AddProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByModel(String model);
}
