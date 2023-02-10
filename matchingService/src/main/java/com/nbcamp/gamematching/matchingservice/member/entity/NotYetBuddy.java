package com.nbcamp.gamematching.matchingservice.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 테스트용
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotYetBuddy {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Member member;

    private Boolean approval = false;

    public NotYetBuddy(Member member) {
        this.member = member;
    }

    public void changeApproval() {
        this.approval = true;
    }
}
