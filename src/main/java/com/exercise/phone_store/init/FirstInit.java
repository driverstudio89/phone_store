package com.exercise.phone_store.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirstInit implements CommandLineRunner {
    private final InitRoles initRoles;
    private final InitAdmin initAdmin;
    private final InitCategories initCategories;

    @Override
    public void run(String... args) throws Exception {
        initRoles.run();
        initAdmin.run();
        initCategories.run();
    }
}
