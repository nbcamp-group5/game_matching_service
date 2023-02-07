package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.member.dto.PageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.PageDto.Content;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public ProfileDto getMyProfile(Member member) {
        Profile myProfile = member.getProfile();
        return new ProfileDto(myProfile);
    }

    @Override
    public PageDto getMyBoards(Long memberId, Pageable pageable) {
        Page<Board> boardList = boardRepository.findAllByMemberId(memberId, pageable);
        List<Content> contents = new ArrayList<>();

        boardList.getContent().forEach(board -> {
            Content newContent = PageDto.Content.builder()
                    .id(board.getId())
                    .nickname(board.getNickname())
                    .boardImageUrl(board.getBoardImageUrl())
                    .content(board.getContent())
                    .build();
            contents.add(newContent);
        });

        return PageDto.builder()
                .contents(contents)
                .numberOfElements(boardList.getNumberOfElements())
                .totalElements(boardList.getNumberOfElements())
                .totalPages(boardList.getTotalPages())
                .currentPage(pageable.getPageNumber())
                .build();
    }
}
