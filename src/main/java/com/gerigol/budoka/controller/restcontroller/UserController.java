package com.gerigol.budoka.controller.restcontroller;


import com.gerigol.budoka.controller.dto.NewUser;
import com.gerigol.budoka.controller.dto.UserDTO;
import com.gerigol.budoka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDTO registerUser(@RequestBody NewUser newUser) {
        return userService.registerUser(newUser);
    }
}
