package com.gerigol.budoka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "\"user\"")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    @JsonIgnore
    @Column(name = "user_id")
    private Long id;

    @Column(
            name = "public_user_id",
            unique = true,
            nullable = false
    )
    private UUID publicId;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "email",
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    @JsonIgnore
    private String password;

    @Column(name = "role")
    @Enumerated
    private Role role;

    @Column(name = "training-days")
    private int trainingDays;

    @OneToOne
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @Column(name = "trainings")
    @ManyToMany(mappedBy = "users")
    private List<Training> trainings;

    @ManyToMany(mappedBy = "users")
    private List<Exam> exams;

    @Column(name = "current_training_amount")
    private int currentTrainingAmount;

    @Column(name = "reuired_training_amount")
    private int requiredTrainingAmount;

    public User(
            UUID publicId,
            String name,
            String email,
            String password,
            Role role
    ) {
        this.publicId = publicId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.trainingDays = 0;
    }
}
