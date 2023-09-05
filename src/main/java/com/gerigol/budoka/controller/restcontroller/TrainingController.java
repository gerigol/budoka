package com.gerigol.budoka.controller.restcontroller;

import com.gerigol.budoka.controller.dto.NewTraining;
import com.gerigol.budoka.controller.dto.TrainingDTO;
import com.gerigol.budoka.domain.Training;
import com.gerigol.budoka.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/training")
@RestController
public class TrainingController {
    private final TrainingService trainingService;

    @PostMapping("/create")
    public TrainingDTO createTraining(@RequestBody NewTraining newTraining) {
        return trainingService.createTraining(newTraining);
    }

}
