package com.nbcamp.gamematching.matchingservice.security;

import com.nbcamp.gamematching.matchingservice.exception.NotFoundException;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        var member = memberRepository.findByEmail(email)
                .orElseThrow(NotFoundException.NotFoundMemberException::new);
        return new UserDetailsImpl(member, member.getEmail(), member.getPassword());
    }

    public Member loadMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(NotFoundException.NotFoundMemberException::new);
    }

}
