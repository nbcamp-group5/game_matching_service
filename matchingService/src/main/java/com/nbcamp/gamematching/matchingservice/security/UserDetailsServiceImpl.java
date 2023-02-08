package com.nbcamp.gamematching.matchingservice.security;

<<<<<<< Updated upstream
=======
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException;
>>>>>>> Stashed changes
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
<<<<<<< Updated upstream
import org.springframework.security.core.userdetails.UsernameNotFoundException;
=======
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member = memberRepository.findByEmail(email)
<<<<<<< Updated upstream
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
=======
                .orElseThrow(() -> new NotFoundException.NotFoundMemberException());
>>>>>>> Stashed changes
        return new UserDetailsImpl(member, member.getEmail(), member.getPassword());
    }
}
