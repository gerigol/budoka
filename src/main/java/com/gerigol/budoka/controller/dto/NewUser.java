package com.gerigol.budoka.controller.dto;

import com.gerigol.budoka.domain.Role;

public record NewUser(String name, String email, String password, Role role) {
}
