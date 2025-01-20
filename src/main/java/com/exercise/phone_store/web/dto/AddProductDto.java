package com.exercise.phone_store.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class AddProductDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String make;

    @NotBlank
    @Size(min = 2, max = 20)
    private String model;

    @NotBlank
    @PositiveOrZero
    @Size(min = 3, max = 20)
    private double price;

    @NotBlank
    @PositiveOrZero
    @Size(min = 3, max = 20)
    private int quantity;

    private String specifications;

//    @Size(min = 3, max = 200)
//    private String picture;

}
