package com.exercise.phone_store.service;

import com.exercise.phone_store.web.dto.AddProductDto;
import com.exercise.phone_store.web.dto.ShowProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    String addProduct(AddProductDto addProductDto, MultipartFile[] pictures);

    List<ShowProductDto> getAllProducts();

    ShowProductDto getProductById(UUID id);

}
