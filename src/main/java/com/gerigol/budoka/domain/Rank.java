package com.gerigol.budoka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rank {

    @Id
    @SequenceGenerator(
            name = "rank_id_sequence",
            sequenceName = "rank_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rank_id_sequence")
    @JsonIgnore
    @Column(name = "rank_id")
    private Long id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @Column(
            name = "rank_type",
            nullable = false
    )
    private RankType rankType;

    @Column(name = "level")
    private int level;

    @Column(name = "required_trainings")
    private int requiredTrainings;


    public Rank(RankType rankType, int level, int requiredTrainings) {
        this.name = level + rankType.name();
        this.rankType = rankType;
        this.level = level;
        this.requiredTrainings = requiredTrainings;
    }
}
