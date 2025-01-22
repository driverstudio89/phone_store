package com.exercise.phone_store.web;

import com.exercise.phone_store.service.UserService;
import com.exercise.phone_store.web.dto.UserRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        String result = userService.registerUser(userRegisterDto);
        System.out.println(result);
        return ResponseEntity.ok().body(result);
    }


}
