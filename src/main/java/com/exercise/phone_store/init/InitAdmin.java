package com.exercise.phone_store.init;

import com.exercise.phone_store.data.UserRepository;
import com.exercise.phone_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitAdmin {
    private final UserService userService;
    private final UserRepository userRepository;

    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }
        userService.initAdmin();
    }
}
