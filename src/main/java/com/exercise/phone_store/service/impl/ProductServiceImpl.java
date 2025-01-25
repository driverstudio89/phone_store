package com.exercise.phone_store.service.impl;

import com.exercise.phone_store.data.ProductRepository;
import com.exercise.phone_store.model.Product;
import com.exercise.phone_store.service.ProductService;
import com.exercise.phone_store.service.exceptions.ObjectNotFoundException;
import com.exercise.phone_store.web.dto.AddProductDto;
import com.exercise.phone_store.web.dto.ShowProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public String addProduct(AddProductDto addProductDto, MultipartFile[] pictures) {
        Optional<Product> byModel = productRepository.findByModel(addProductDto.getModel());
        System.out.println();

        if (byModel.isPresent()) {
            return "Product with model "+ addProductDto.getModel() + " already exists";
        }

        Product product = modelMapper.map(addProductDto, Product.class);
        product.setPictures(new ArrayList<>());

        for (MultipartFile picture : pictures) {
            String pictureName = picture.getOriginalFilename();
            File file = new File("src/main/resources/img/product_image"+pictureName);
            try {
                picture.transferTo(file.toPath());
                product.getPictures().add(file.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productRepository.save(product);
        return product.toString();
    }

    @Transactional
    @Override
    public List<ShowProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ShowProductDto> allProductsDtoList = allProducts.stream()
                .map(product -> modelMapper.map(product, ShowProductDto.class)).toList();
        return allProductsDtoList;
    }

    @Transactional
    @Override
    public ShowProductDto getProductById(UUID id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ObjectNotFoundException("Product not found", id);
        }
        return modelMapper.map(optionalProduct.get(), ShowProductDto.class);
    }

    @Override
    public String deleteProductById(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ObjectNotFoundException("Product not found", id);
        }
        productRepository.deleteById(id);
        return "Product " + optionalProduct.get().getMake() + optionalProduct.get().getModel()
                + " deleted successfully";
    }
}
