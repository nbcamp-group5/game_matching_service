package com.nbcamp.gamematching.matchingservice.member.entity;

import com.nbcamp.gamematching.matchingservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    @Column
    private Long buddyId;

    @Column(nullable = false)
    private int manner = 100;

    @Column
    private Long noBuddyId;

    @Builder
    public Member(String email, String password, String nickname, MemberRoleEnum role, Long buddyId, int manner, Long noBuddyId) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.buddyId = buddyId;
        this.manner = manner;
        this.noBuddyId = noBuddyId;
    }

    public void changeRole(MemberRoleEnum role) {
        this.role = role;
    }

    public boolean validateUser(Member member) {
        return this.id.equals(member.getId());
    }
}