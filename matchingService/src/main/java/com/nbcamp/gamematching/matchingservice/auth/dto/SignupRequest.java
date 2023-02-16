package com.nbcamp.gamematching.matchingservice.auth.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {

    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @Pattern(regexp = "\\w{8,16}", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;
    @Pattern(regexp = "\\w{2,8}", message = "닉네임 형식이 올바르지 않습니다.")
    private String nickname;
    private String profileImageUrl;

}

