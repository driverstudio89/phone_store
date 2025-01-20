package com.exercise.phone_store.service.util;

import com.exercise.phone_store.web.dto.AddProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class Util {

    public AddProductDto convertToAddProductDto(String inputJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputJson, AddProductDto.class);
    }
}
