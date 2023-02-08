package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.matchinglog.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.matchinglog.repository.MatchingLogRepository;
import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto.BoardContent;
import com.nbcamp.gamematching.matchingservice.member.dto.MatchingLogPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.MatchingLogPageDto.MatchingLogContent;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Board;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final MatchingLogRepository matchingLogRepository;

    @Override
    public ProfileDto getMyProfile(Member member) {
        Profile myProfile = member.getProfile();
        return new ProfileDto(myProfile);
    }

    @Override
    public BoardPageDto getMyBoards(Long memberId, Pageable pageable) {
        Page<Board> boardList = boardRepository.findAllByMemberId(memberId, pageable);

        List<BoardContent> boardContents = boardList.getContent().stream().map(BoardContent::new)
                .collect(Collectors.toList());

        return BoardPageDto.builder()
                .contents(boardContents)
                .numberOfElements(boardList.getNumberOfElements())
                .totalElements(boardList.getNumberOfElements())
                .totalPages(boardList.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .build();
    }

    @Override
    public MatchingLogPageDto getMyMatchingList(Member member, Pageable pageable) {
        // TODO: matchings 내부의 members 리스트에서 내가 있는지 어떻게 조회하지? => QueryDSL? member 리스트는 MatchingLog에!
        Page<MatchingLog> myMatchingList = matchingLogRepository.findAllByMember(member);

        List<MatchingLogContent> matchingLogContents = myMatchingList.getContent().stream()
                .map(MatchingLogContent::new).collect(Collectors.toList());

        return MatchingLogPageDto.builder()
                .contents(matchingLogContents)
                .numberOfElements(myMatchingList.getNumberOfElements())
                .totalPages(myMatchingList.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .totalElements(myMatchingList.getNumberOfElements()).build();
    }

//    @Override
//    public BoardPageDto getMyBuddies(Member member, Pageable pageable) {
//
//    }
}
