package com.exercise.phone_store.web;

import com.exercise.phone_store.service.ProductService;
import com.exercise.phone_store.service.util.Util;
import com.exercise.phone_store.web.dto.AddProductDto;
import com.exercise.phone_store.web.dto.ShowProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final Util util;

    @PostMapping(value = "/product/add-product", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> addProduct(
            @RequestPart(required = false) MultipartFile[] pictures,
            @RequestPart(required = false) @Valid AddProductDto addProductDto ){
        return ResponseEntity.ok(productService.addProduct(addProductDto, pictures));
    }

    @GetMapping(value = "/all-products")
    public ResponseEntity<List<ShowProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ShowProductDto> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
