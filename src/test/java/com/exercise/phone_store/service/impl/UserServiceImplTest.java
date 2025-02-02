package com.exercise.phone_store.service.impl;

import com.exercise.phone_store.data.RoleRepository;
import com.exercise.phone_store.data.UserRepository;
import com.exercise.phone_store.model.Role;
import com.exercise.phone_store.model.User;
import com.exercise.phone_store.model.enums.UserRole;
import com.exercise.phone_store.service.UserService;
import com.exercise.phone_store.web.dto.UserRegisterDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private static final String TEST_EMAIL = "user@mail.bg";
    private static final String TEST_PASSWORD = "123456";

    private UserService userService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Captor
    private ArgumentCaptor<User> userCapture;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(mockUserRepository, modelMapper, mockPasswordEncoder, mockRoleRepository); {
        }
    }

    private final User testUser = new User()
            .setEmail(TEST_EMAIL)
            .setPassword(TEST_PASSWORD)
            .setRoles(List.of(
                    new Role().setRole(UserRole.USER),
                    new Role().setRole(UserRole.ADMIN)
            ))
            .setFirstName("User")
            .setLastName("Userov")
            .setPhoneNumber("0888888888")
            .setCity("Sliven")
            .setZip("8800")
            .setAddress("ul. Street 11")
            .setCountry("Bulgaria");

    @Test
    void testUserRegistrationSuccess() {
        UserRegisterDto userRegisterDto = createUserRegisterDto();
        userService.registerUser(userRegisterDto);
        verify(mockUserRepository).save(userCapture.capture());
        User actualUser = userCapture.getValue();
        Assertions.assertEquals(userRegisterDto.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(userRegisterDto.getPassword() +
                userRegisterDto.getPassword(), actualUser.getPassword());
    }

    @Test
    void testUserRegistration_EmailAlreadyExists() {
        UserRegisterDto userRegisterDto = createUserRegisterDto();
        userService.registerUser(userRegisterDto);
        when(mockUserRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(testUser));

        Assertions.assertEquals("User with email " + TEST_EMAIL + " is already registered",
                userService.registerUser(userRegisterDto));
    }



    private UserRegisterDto createUserRegisterDto() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail(TEST_EMAIL);
        userRegisterDto.setPassword(TEST_PASSWORD);

        when(mockPasswordEncoder.encode(userRegisterDto.getPassword()))
                .thenReturn(userRegisterDto.getPassword() + userRegisterDto.getPassword());
        return userRegisterDto;
    }

}
