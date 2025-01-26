package com.exercise.phone_store.service.impl;

import com.exercise.phone_store.data.RoleRepository;
import com.exercise.phone_store.data.UserRepository;
import com.exercise.phone_store.model.Role;
import com.exercise.phone_store.model.User;
import com.exercise.phone_store.model.enums.UserRole;
import com.exercise.phone_store.service.UserService;
import com.exercise.phone_store.web.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        Optional<User> byEmail = userRepository.findByEmail(userRegisterDto.getEmail());
        if (byEmail.isPresent()) {
            return ("User with email " + userRegisterDto.getEmail() + " is already registered");
        }

        User user = modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Role role = roleRepository.findByRole(UserRole.USER);
        user.getRoles().add(role);
        userRepository.save(user);

        return String.format(user.getEmail() + " is registered");
    }

    @Override
    public void initAdmin() {
        User admin = new User();
        Role adminRole = roleRepository.findByRole(UserRole.ADMIN);
        admin.setEmail("admin@admin.bg");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setFirstName("Admin");
        admin.setLastName("Adminov");
        admin.getRoles().add(adminRole);
        userRepository.save(admin);
    }
}
