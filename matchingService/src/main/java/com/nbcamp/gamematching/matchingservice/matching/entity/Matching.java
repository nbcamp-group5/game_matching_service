package com.nbcamp.gamematching.matchingservice.matching.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@NoArgsConstructor
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String playMode;

    @Column(nullable = false)
    private String gameName;

    @Column(nullable = false)
    private String memberNumbers;

}