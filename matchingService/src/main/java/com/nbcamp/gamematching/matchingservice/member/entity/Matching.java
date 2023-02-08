package com.nbcamp.gamematching.matchingservice.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 테스트용
@Entity
@Getter
@NoArgsConstructor
public class Matching {

    @Id
    @GeneratedValue
    private Long id;
    private Long memberId;


}
