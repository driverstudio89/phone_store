package com.exercise.phone_store.data;

import com.exercise.phone_store.model.Category;
import com.exercise.phone_store.model.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategory(CategoryType category);
}
