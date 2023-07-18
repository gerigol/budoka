package com.gerigol.budoka.utility;

import com.gerigol.budoka.controller.dto.LoginCredentials;
import com.gerigol.budoka.controller.dto.NewUser;
import com.gerigol.budoka.controller.dto.UserDTO;
import com.gerigol.budoka.domain.AuthUser;
import com.gerigol.budoka.domain.User;

import java.util.UUID;

public class UserMapper {
    public static AuthUser toAuthUser(User user) {
        return new AuthUser(user.getEmail(), user.getPassword(), user.getRole());
    }

    public static LoginCredentials toLoginCredential(User user, String token) {
        return new LoginCredentials(user.getPublicId(), user.getRole(), user.getName(), token);
    }

    public static User toUser(NewUser newUser) {
        return new User(UUID.randomUUID(), newUser.name(), newUser.email(), newUser.password(), newUser.role());
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getPublicId(), user.getName(), user.getEmail(), user.getRole());
    }
}
