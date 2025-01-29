package com.exercise.phone_store.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ShowProductDto {

    private UUID id;

    private String make;

    private String model;

    private double price;

    private List<String> pictures;

    private String specifications;

    private String category;
}
