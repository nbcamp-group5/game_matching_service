package com.nbcamp.gamematching.matchingservice.member.repository;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Member> findAll(Pageable pageable);

    Optional<Member> findByProfileNickname(String friendNick);
}
