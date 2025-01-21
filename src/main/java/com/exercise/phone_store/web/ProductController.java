package com.exercise.phone_store.web;

import com.exercise.phone_store.model.Product;
import com.exercise.phone_store.service.ProductService;
import com.exercise.phone_store.service.util.Util;
import com.exercise.phone_store.web.dto.AddProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

}
