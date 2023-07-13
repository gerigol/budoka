package com.gerigol.budoka.utility;

import com.gerigol.budoka.domain.AuthUser;
import com.gerigol.budoka.domain.User;

public class UserMapper {
    public static AuthUser toAuthUser(User user) {
        return new AuthUser(user.getEmail(), user.getPassword(), user.getRole());
    }
}
