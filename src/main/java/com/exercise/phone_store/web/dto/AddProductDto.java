package com.exercise.phone_store.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddProductDto {

    @NotBlank(message = "you need to enter make")
    @Size(min = 2, max = 20, message = "make length is between 2 and 20 characters")
    private String make;

    @NotBlank(message = "you need to enter model")
    @Size(min = 2, max = 20, message = "model length is between 2 and 20 characters")
    private String model;

    @NotNull(message = "price is required")
    @PositiveOrZero(message = "price need to be positive number")
    private double price;

    @NotNull(message = "quantity is required")
    @PositiveOrZero(message = "quantity need to be positive number")
    private int quantity;

    private String specifications;

}
