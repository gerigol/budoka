package com.gerigol.budoka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Training {

    @Id
    @SequenceGenerator(
            name = "training_id_sequence",
            sequenceName = "training_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "training_id_sequence"
    )
    @JsonIgnore
    @Column(name = "training_id")
    private Long id;

    @Column(
            name = "public_training_id",
            unique = true,
            nullable = false
    )
    private UUID publicId;

    @Column(name = "name")
    private String name;

   @Column(
           name = "start_date_time",
           nullable = false
   )
    private LocalDateTime startDateTime;

    @Column(
            name = "end_date_time",
            nullable = false
    )
    private LocalDateTime endDateTime;

    @OneToOne
    private Location location;

    @Column(name = "trainers")
    @ManyToMany
    private List<User> trainers;

    @ManyToMany
    @JoinTable(
            name = "user_to_training",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> trainees;


    public Training(UUID publicId,
                    String name,
                    LocalDateTime startDateTime,
                    LocalDateTime endDateTime,
                    Location location) {
        this.publicId = publicId;
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.trainers = new ArrayList<>();
        this.trainees = new ArrayList<>();
    }

    public void addTrainersToTraining(List<User> trainers) {
        this.trainers.addAll(trainers);
    }
}
