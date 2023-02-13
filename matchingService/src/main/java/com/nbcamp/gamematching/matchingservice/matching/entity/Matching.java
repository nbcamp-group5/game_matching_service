package com.nbcamp.gamematching.matchingservice.matching.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity(name = "Matching")
@Getter
@NoArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private PlayModeEnum playModeEnum ;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GameType game;

    @Column(nullable = false)
    private int participantNumbers;

}