package com.gerigol.budoka.controller.dto;

import com.gerigol.budoka.domain.Location;
import com.gerigol.budoka.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record TrainingDTO(
        UUID publicId,
        String name,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Location location,
        List<User> trainers,
        List<User> trainees
) {
}
