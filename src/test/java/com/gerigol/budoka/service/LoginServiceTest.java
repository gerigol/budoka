package com.gerigol.budoka.service;

import com.gerigol.budoka.controller.dto.LoginCredentials;
import com.gerigol.budoka.controller.dto.LoginRequest;
import com.gerigol.budoka.domain.Role;
import com.gerigol.budoka.domain.User;
import com.gerigol.budoka.security.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;
    @Mock
    private JWTService jwtService;
    @InjectMocks
    private LoginService loginService;
    private LoginRequest loginRequest;
    private UUID publicID;

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest("test@email.com", "pw");
        publicID = UUID.randomUUID();
    }

    @DisplayName("Test verify user - User is valid")
    @Test
    void test_valid_user_verification() {
        //Arrange
        User user = new User(publicID, "Test", loginRequest.email(), "pw", Role.OTHER);
        when(userService.getUserByEmail(anyString())).thenReturn(user);

        String token = "Bearer token";
        when(jwtService.generateToken(any(Authentication.class))).thenReturn(token);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        );
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(authentication);

        //Act
        LoginCredentials result = loginService.verifyUser(loginRequest);

        //Assert
        LoginCredentials expected = new LoginCredentials(publicID, Role.OTHER, "Test", "Bearer token");
        assertEquals(expected, result);
    }

    @DisplayName("Test verify user - User is invalid")
    @Test
    void test_invalid_user_verification() {
        //Arrange
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        );
        when(authenticationManager.authenticate(authentication))
                .thenThrow(new AuthenticationServiceException("Invalid credentials"));

        //Assert
        assertThrows(AuthenticationException.class, () -> loginService.verifyUser(loginRequest));
        verify(userService, never()).getUserByEmail(anyString());
        verify(jwtService, never()).generateToken(any(Authentication.class));
    }
}