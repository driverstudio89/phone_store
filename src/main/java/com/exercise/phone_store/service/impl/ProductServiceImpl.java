package com.exercise.phone_store.service.impl;

import com.exercise.phone_store.data.CategoryRepository;
import com.exercise.phone_store.data.ProductRepository;
import com.exercise.phone_store.model.Category;
import com.exercise.phone_store.model.Product;
import com.exercise.phone_store.model.enums.CategoryType;
import com.exercise.phone_store.service.ProductService;
import com.exercise.phone_store.service.exceptions.ObjectNotFoundException;
import com.exercise.phone_store.web.dto.AddProductDto;
import com.exercise.phone_store.web.dto.ShowProductDto;
import com.exercise.phone_store.web.dto.UpdateProductDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final Gson gson;

    @Override
    @Transactional
    public String addProduct(AddProductDto addProductDto, MultipartFile[] pictures) {
        Optional<Product> byModel = productRepository.findByModel(addProductDto.getModel());

        if (byModel.isPresent()) {
            return "Product with model " + addProductDto.getModel() + " already exists";
        }

        Product product = modelMapper.map(addProductDto, Product.class);

        Optional<Category> byCategory = categoryRepository.
                findByCategory(CategoryType.valueOf(addProductDto.getCategory().toUpperCase()));

        product.setCategory(byCategory.orElseThrow(() -> new ObjectNotFoundException("Category not found",
                addProductDto.getCategory())));

        product.setCreated(LocalDateTime.now());

        product.setPictures(new ArrayList<>());
        if (pictures == null || pictures.length == 0) {
            pictures = new MultipartFile[0];
        }
        mappingPictures(product, pictures);

        productRepository.save(product);

        ShowProductDto showProductDto = modelMapper.map(product, ShowProductDto.class);
        showProductDto.setCategory(product.getCategory().getCategory().toString());
        return gson.toJson(showProductDto);
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

    @Transactional
    @Override
    public String deleteProductById(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ObjectNotFoundException("Product not found", id);
        }
        List<String> pictures = optionalProduct.get().getPictures();
        System.out.println();
        for (String picture : pictures) {
            try {
                Files.deleteIfExists(Path.of(picture));
            } catch (IOException e) {
                throw new ObjectNotFoundException("Picture not found", picture);
            }
        }
        productRepository.deleteById(id);
        return "Product " + optionalProduct.get().getMake() + optionalProduct.get().getModel()
                + " deleted successfully";
    }

    private void mappingPictures(Product product, MultipartFile[] pictures) {
        for (MultipartFile picture : pictures) {
            String pictureName = picture.getOriginalFilename();
            File file = new File("src/main/resources/img/product_image/" + pictureName);
            try {
                Files.createDirectories(Paths.get("src/main/resources/img/product_image/"));
                picture.transferTo(file.toPath());
                product.getPictures().add(file.getPath());
            } catch (IOException e) {
                throw new ObjectNotFoundException("Picture not found", pictureName);
            }
        }
    }

    @Transactional
    @Override
    public List<ShowProductDto> getNewestProducts() {
        Optional<List<Product>> optionalProducts = productRepository.
                findTop12ByCreatedAfter(LocalDateTime.now().minusWeeks(1));
        System.out.println();
        return optionalProducts.map(products -> products.stream()
                .map(product -> modelMapper.map(product, ShowProductDto.class))
                .toList()).orElseGet(List::of);
    }

    @Override
    @Transactional
    public String updateProduct(UUID id, UpdateProductDto updateProductDto, MultipartFile[] pictures) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ObjectNotFoundException("Product not found", id);
        }
        Product product = optionalProduct.get();
        product.setUpdated(LocalDateTime.now());

        if (updateProductDto.getMake() != null) {
            product.setMake(updateProductDto.getMake());
        }

        if (updateProductDto.getModel() != null) {
            product.setModel(updateProductDto.getModel());
        }

        if (updateProductDto.getPrice() != null) {
            product.setPrice(updateProductDto.getPrice());
        }

        if (updateProductDto.getQuantity() != null) {
            product.setQuantity(updateProductDto.getQuantity());
        }

        if (updateProductDto.getSpecifications() != null) {
            product.setSpecifications(updateProductDto.getSpecifications());
        }

        if (updateProductDto.getCategory() != null) {
            Optional<Category> byCategory = categoryRepository.
                    findByCategory(CategoryType.valueOf(updateProductDto.getCategory().toUpperCase()));

            product.setCategory(byCategory.orElseThrow(() -> new ObjectNotFoundException("Category not found",
                    updateProductDto.getCategory())));
        }

        if (pictures != null && pictures.length > 0) {
            mappingPictures(product, pictures);
        }

        productRepository.save(product);

        ShowProductDto showProductDto = modelMapper.map(product, ShowProductDto.class);
        showProductDto.setCategory(product.getCategory().getCategory().toString());
        return gson.toJson(showProductDto);
    }
}

