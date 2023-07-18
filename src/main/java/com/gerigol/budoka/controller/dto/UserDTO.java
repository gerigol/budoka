package com.gerigol.budoka.controller.dto;

import com.gerigol.budoka.domain.Role;

import java.util.UUID;

public record UserDTO(UUID publicID, String name, String email, Role role) {
}
