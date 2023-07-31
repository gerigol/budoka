package com.gerigol.budoka.controller.dto;

import com.gerigol.budoka.domain.Role;

import java.util.UUID;

public record LoginCredentials(UUID id, Role role, String name, String token) {
}
