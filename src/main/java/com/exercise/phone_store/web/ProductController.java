package com.exercise.phone_store.web;

import com.exercise.phone_store.service.ProductService;
import com.exercise.phone_store.web.dto.AddProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product/add-product")
    public ResponseEntity<String> addProduct(@RequestBody AddProductDto addProductDto) {
        String result = productService.addProduct(addProductDto);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }



}
