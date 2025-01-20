package com.exercise.phone_store.service;

import com.exercise.phone_store.web.dto.AddProductDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    String addProduct(AddProductDto addProductDto, MultipartFile[] pictures);
}
