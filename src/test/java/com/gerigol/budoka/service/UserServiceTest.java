package com.gerigol.budoka.service;

import com.gerigol.budoka.controller.dto.NewUser;
import com.gerigol.budoka.controller.dto.UserDTO;
import com.gerigol.budoka.domain.Role;
import com.gerigol.budoka.domain.User;
import com.gerigol.budoka.exception.BadRequestException;
import com.gerigol.budoka.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UUID publicID;
    private final String email = "test@example.com";
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        publicID = UUID.randomUUID();
    }

    @DisplayName("Test getUserByEmail - User exists")
    @Test
    void test_getUserByEmail_userExists() {
        //Arrange
        User expectedUser = new User();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));

        //Act
        User resultUser = userService.getUserByEmail(email);

        //Assert
        assertEquals(expectedUser, resultUser);
    }

    @DisplayName("Test getUserByEmail - User does not exist")
    @Test
    void test_getUserByEmail_userDoesNotExist() {
        //Arrange
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        //Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByEmail(email));
    }

    @DisplayName("Test registerUser - Registration successful")
    @Test
    void test_register_user_happy_path() {
        //Arrange
        NewUser newUser = new NewUser("Test", email, "pw", Role.OTHER);
        User user = new User(publicID, "Test", email, "pw", Role.OTHER);
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encrypted password");

        //Act
        UserDTO result = userService.registerUser(newUser);

        //Assert
        UserDTO expected = new UserDTO(publicID, "Test", email, Role.OTHER);
        assertEquals(expected, result);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals("Test", capturedUser.getName());
        assertEquals(email, capturedUser.getEmail());
        assertEquals("encrypted password", capturedUser.getPassword());
    }

    @DisplayName("Test registerUser - User already exists")
    @Test
    void test_throw_error_on_register_when_user_already_exists() {
        //Arrange
        NewUser newUser = new NewUser("", "", "pw", Role.OTHER);
        when(userRepository.existsByEmail(any(String.class))).thenReturn(true);

        //Assert
        assertThrows(BadRequestException.class, () -> userService.registerUser(newUser));
    }
}