package com.gerigol.budoka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exam {

    @Id
    @SequenceGenerator(
            name = "exam_id_sequence",
            sequenceName = "exam_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "exam_id_sequence"
    )
    @JsonIgnore
    @Column(name = "exam_id")
    private Long id;

    @Column(
            name = "public_exam_id",
            unique = true,
            nullable = false
    )
    private UUID publicId;

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

    @Column(name = "users")
    @ManyToMany
    @JoinTable(
            name = "user_to_exam",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id" +
                    "")
    )
    private Set<User> users;

    @Column(
            name = "ranks",
            nullable = false
    )
    @OneToMany
    @JoinColumn(name = "exam_id")
    private Set<Rank> ranks;
}
