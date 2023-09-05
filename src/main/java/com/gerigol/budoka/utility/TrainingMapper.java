package com.gerigol.budoka.utility;

import com.gerigol.budoka.controller.dto.TrainingDTO;
import com.gerigol.budoka.domain.Training;

public class TrainingMapper {
    public static TrainingDTO toDTO(Training training) {
        return new TrainingDTO(training.getPublicId(),
                training.getName(),
                training.getStartDateTime(),
                training.getEndDateTime(),
                training.getLocation(),
                training.getTrainers(),
                training.getUsers());
    }
}
