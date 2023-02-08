package com.nbcamp.gamematching.matchingservice.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// 테스트용
@Entity
public class NotYetBuddy {

    @Id
    @GeneratedValue
    private Long id;
}
