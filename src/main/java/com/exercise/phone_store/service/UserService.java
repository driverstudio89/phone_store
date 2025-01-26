package com.exercise.phone_store.service;

import com.exercise.phone_store.web.dto.UserRegisterDto;

public interface UserService {
    String registerUser(UserRegisterDto user);

    void initAdmin();
}
