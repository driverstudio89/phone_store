package com.exercise.phone_store.service.impl;

import com.exercise.phone_store.data.ProductRepository;
import com.exercise.phone_store.model.Product;
import com.exercise.phone_store.service.ProductService;
import com.exercise.phone_store.web.dto.AddProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public String addProduct(AddProductDto addProductDto, MultipartFile[] pictures) {
        Optional<Product> byModel = productRepository.findByModel(addProductDto.getModel());

        if (byModel.isPresent()) {
            return "Product with model "+ addProductDto.getModel() + " already exists";
        }

        Product product = modelMapper.map(addProductDto, Product.class);
        productRepository.save(product);

        return "Product " + addProductDto.getMake() + " " + addProductDto.getModel() + " added";
    }
}
