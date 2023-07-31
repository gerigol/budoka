package com.gerigol.budoka.controller.restcontroller;

import com.gerigol.budoka.controller.dto.LoginCredentials;
import com.gerigol.budoka.controller.dto.LoginRequest;
import com.gerigol.budoka.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
@RestController
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public LoginCredentials verifyUser(@RequestBody LoginRequest loginRequest) {
        return loginService.verifyUser(loginRequest);
    }
}
