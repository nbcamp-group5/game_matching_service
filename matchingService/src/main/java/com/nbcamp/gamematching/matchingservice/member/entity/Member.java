package com.nbcamp.gamematching.matchingservice.member.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    public String password;

    @Embedded
    private Profile profile;



    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    @Builder
    public Member(String password, Profile profile) {
        this.password = password;
        this.profile = profile;
    }

    /**
     * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
     */
    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "buddy")
    private List<Member> buddies = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<NotYetBuddy> notYetBuddies = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Matching> matchings = new ArrayList<>();


    /**
     * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
     */


    /**
     * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
     */
}
