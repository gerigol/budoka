package com.gerigol.budoka.service;

import com.gerigol.budoka.controller.dto.LocationDTO;
import com.gerigol.budoka.controller.dto.NewTraining;
import com.gerigol.budoka.controller.dto.TrainingDTO;
import com.gerigol.budoka.domain.Location;
import com.gerigol.budoka.domain.Training;
import com.gerigol.budoka.domain.User;
import com.gerigol.budoka.repository.TrainingRepository;
import com.gerigol.budoka.utility.LocationMapper;
import com.gerigol.budoka.utility.TrainingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TrainingService {

    private final UserService userService;
    private final LocationService locationService;
    private final TrainingRepository trainingRepository;

    public TrainingDTO createTraining(NewTraining newTraining) {

        // Get or create the location
        Location location = getOrCreateLocation(newTraining.locationDTO());

        verifyNewTraining(newTraining);
        Training training = new Training(UUID.randomUUID(),
                newTraining.name(),
                newTraining.startDateTime(),
                newTraining.endDateTime(),
                location);

        // Add trainer(s) to the training
        if (Objects.nonNull(newTraining.trainerIds()) && !newTraining.trainerIds().isEmpty()) {
            List<User> trainers = userService.getUsersByPublicId(newTraining.trainerIds());
            training.addTrainersToTraining(trainers);
        }

        // save the Training with location into db
        training = trainingRepository.save(training);
        // convert the created training to dto and return it
        return TrainingMapper.toDTO(training);

    }

    private void verifyNewTraining(NewTraining newTraining) {
        if (newTraining.name().isBlank()) {
            throw new IllegalArgumentException("Training name cannot be empty."); //TODO: Create proper custom exception handling!
        }

        if (newTraining.startDateTime().isAfter(newTraining.endDateTime()) ||
                newTraining.startDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date and time must be before end date and time.");  //TODO: Create proper custom exception handling!
        }
    }

    private Location getOrCreateLocation(LocationDTO locationDTO) {
        // Check if the location exists, then get or create the location
        if (locationService.locationExists(locationDTO.name()))
            return locationService.getLocationByName(locationDTO.name());
        return locationService.createLocation(LocationMapper.toLocation(locationDTO));

    }
}
