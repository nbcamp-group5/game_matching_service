package com.nbcamp.gamematching.matchingservice.member;


import com.nbcamp.gamematching.matchingservice.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name="userId")
    private String username;

    private String nickName;

    private String name;

    @Column(name="userPw")
    private String password;

    private String refreshToken;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<RoleType> role = new ArrayList<>();

    @Builder
    public Member(String username, String password, String name, String nickName,List<RoleType> role) {
        this.username = username;
        this.password = password;
        this.name=name;
        this.nickName = nickName;
        if(username.equals("master"))
            this.role=Collections.singletonList(RoleType.ROLE_ADMIN);
        else
            this.role= Collections.singletonList(RoleType.ROLE_USER);
    }

    public void addRole(RoleType roleType){
        this.role.add(roleType);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
