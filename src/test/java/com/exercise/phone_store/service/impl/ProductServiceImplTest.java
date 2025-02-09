package com.exercise.phone_store.service.impl;

import com.exercise.phone_store.data.CategoryRepository;
import com.exercise.phone_store.data.ProductRepository;
import com.exercise.phone_store.model.Category;
import com.exercise.phone_store.model.Product;
import com.exercise.phone_store.model.enums.CategoryType;
import com.exercise.phone_store.service.ProductService;
import com.exercise.phone_store.web.dto.AddProductDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    private static final String TEST_IMAGE_URL = "src/test/resources/xiaomi-redmi-note-8-pro.jpeg";
    File file = new File(TEST_IMAGE_URL);
    private static final int TEST_QUANTITY = 4;

    private static final String TEST_MAKE = "TestProduct";
    private static final String TEST_MODEL = "TestModel";
    private static final String TEST_SPECIFICATIONS = "Test specifications";
    private static final List<String> TEST_IMAGE = List.of(TEST_IMAGE_URL);
    private static final double TEST_PRICE = 123.90;
    private static final LocalDateTime TEST_CREATED = LocalDateTime.now();

    private ProductService toTest;

    private final ModelMapper modelMapper = new ModelMapper();

    private final Gson gson = new Gson();

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private CategoryRepository mockCategoryRepository;

    CategoryType categoryType = CategoryType.SMARTPHONE;

    Category category = new Category(categoryType);


    @BeforeEach
    void setUp() {
        toTest = new ProductServiceImpl(mockProductRepository, modelMapper, mockCategoryRepository, gson);


    }

    private final Product testProduct = new Product()
            .setMake(TEST_MAKE)
            .setModel(TEST_MODEL)
            .setPrice(TEST_PRICE)
            .setSpecifications(TEST_SPECIFICATIONS)
            .setPictures(TEST_IMAGE)
            .setCategory(new Category(CategoryType.SMARTPHONE))
            .setCreated(TEST_CREATED);


    @Test
    void testAddProductSuccess() {
        AddProductDto addProductDto = createProductDto();
        MultipartFile[] pictures = createMultipartFile();

        when(mockCategoryRepository.findByCategory(categoryType)).thenReturn(Optional.of(category));

        toTest.addProduct(addProductDto, pictures);

        verify(mockProductRepository).save(productCaptor.capture());
        Product actualProduct = productCaptor.getValue();
        Assertions.assertEquals(TEST_MAKE, actualProduct.getMake());
        Assertions.assertEquals(TEST_MODEL, actualProduct.getModel());
        Assertions.assertEquals(TEST_PRICE, actualProduct.getPrice());
        Assertions.assertEquals(TEST_SPECIFICATIONS, actualProduct.getSpecifications());
        File image = new File(actualProduct.getPictures().getFirst());
        Assertions.assertArrayEquals(file.listFiles(), image.listFiles());
    }

    private MultipartFile[] createMultipartFile(){
        try (final FileInputStream input = new FileInputStream(file)) {
            MultipartFile multipartFile = new MockMultipartFile(
                    file.getName(), file.getName(), file.getName(), input);
            return new MultipartFile[]{multipartFile};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AddProductDto createProductDto() {
        AddProductDto addProductDto = new AddProductDto();
        addProductDto.setMake(TEST_MAKE);
        addProductDto.setModel(TEST_MODEL);
        addProductDto.setPrice(TEST_PRICE);
        addProductDto.setQuantity(TEST_QUANTITY);
        addProductDto.setCategory("smartphone");
        addProductDto.setSpecifications(TEST_SPECIFICATIONS);
        return addProductDto;
    }

}
