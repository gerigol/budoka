package com.gerigol.budoka.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NewTraining(
        String name,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        LocationDTO locationDTO,
        List<UUID> trainerIds,
        List<UUID> traineeIds
) {
}
