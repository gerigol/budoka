package com.gerigol.budoka.service;

import com.gerigol.budoka.controller.dto.LoginCredentials;
import com.gerigol.budoka.controller.dto.LoginRequest;
import com.gerigol.budoka.domain.User;
import com.gerigol.budoka.security.JWTService;
import com.gerigol.budoka.utility.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTService jwtService;

    public LoginCredentials verifyUser(LoginRequest loginRequest) {
        Authentication authentication = authenticateUser(loginRequest);
        User user = userService.getUserByEmail(loginRequest.email());
        String token = jwtService.generateToken(authentication);
        return UserMapper.toLoginCredential(user, token);
    }

    private Authentication authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
