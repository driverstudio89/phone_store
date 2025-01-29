package com.exercise.phone_store.init;

import com.exercise.phone_store.data.CategoryRepository;
import com.exercise.phone_store.model.Category;
import com.exercise.phone_store.model.enums.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitCategories {

    private final CategoryRepository categoryRepository;

    public void run(String... args) throws Exception {
        if (categoryRepository.count() > 0) {
            return;
        }
        for (CategoryType categoryType : CategoryType.values()) {
            Category category = new Category(categoryType);
            categoryRepository.save(category);
        }

    }
}
