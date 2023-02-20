package com.nbcamp.gamematching.matchingservice.security;

import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private MemberRepository memberRepository;


    // userRequest 는 code를 받아서 accessToken을 응답 받은 객체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회

        // code를 통해 구성한 정보
        System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
        System.out.println(userRequest);
        System.out.println(oAuth2User);

        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        // Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
        OAuth2UserInfo oAuth2UserInfo = null;
//        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
//            System.out.println("구글 로그인 요청~~");
//            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
//        }
      userRequest.getClientRegistration().getRegistrationId().equals("kakao");
            System.out.println("카카오 로그인 요청~~");
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());



        System.out.println("oAuth2UserInfo.getProvider() : " + oAuth2UserInfo.getProvider());
        System.out.println("oAuth2UserInfo.getProviderId() : " + oAuth2UserInfo.getProviderId());
        System.out.println("getname :" + oAuth2UserInfo.getName());
        System.out.println("getemail :" + oAuth2UserInfo.getEmail());
        System.out.println(oAuth2User.getAttributes());
        System.out.println(oAuth2User.getName());
        Optional<Member> userOptional =
                memberRepository.findByEmail(oAuth2UserInfo.getEmail());

        Member user;
        if (userOptional.isPresent()) {
            user = Member.builder()
                    .email(oAuth2UserInfo.getEmail().substring(2))
                    .password(oAuth2UserInfo.getProviderId().substring(0,8))
                    .role(MemberRoleEnum.USER)
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .profile(Profile.builder()
                            .nickname(oAuth2UserInfo.getName()).build())
                    .build();
            memberRepository.save(user);
        } else {
            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            user = Member.builder()
                    .email(oAuth2UserInfo.getEmail().substring(2))
                    .password(oAuth2UserInfo.getProviderId().substring(0,8))
                    .role(MemberRoleEnum.USER)
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .profile(Profile.builder()
                            .nickname(oAuth2UserInfo.getName()).build())
                    .build();
            memberRepository.save(user);

        }


        return new UserDetailsImpl(user,oAuth2User.getAttributes());
    }


}
