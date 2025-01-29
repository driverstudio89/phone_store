package com.exercise.phone_store.web;

import com.exercise.phone_store.service.ProductService;
import com.exercise.phone_store.web.dto.ShowProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ShowProductDto>> newestProducts() {
        return ResponseEntity.ok(productService.getNewestProducts());
    }
}
