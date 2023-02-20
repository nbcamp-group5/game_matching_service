package com.nbcamp.gamematching.matchingservice.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SigninRequest {

    private String email;
    private String password;

    public SigninRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
