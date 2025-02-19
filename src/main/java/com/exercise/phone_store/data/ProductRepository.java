package com.exercise.phone_store.data;

import com.exercise.phone_store.model.Category;
import com.exercise.phone_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByModel(String model);

    Optional<List<Product>> findTop12ByCreatedAfter(LocalDateTime createdAfter);

    List<Product> findAllByCategory(Category category);
}
