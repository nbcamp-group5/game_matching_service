package com.nbcamp.gamematching.matchingservice.board.dto;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class AnonymousBoardAdminDto {

    private Long id;
    private String boardImage;
    private String nickname;
    private LocalDateTime createAt;
    private String content;

    public AnonymousBoardAdminDto(AnonymousBoard board) {
        this.id = board.getId();
        this.boardImage = board.getBoardImage();
        this.nickname = board.getNickname();
        this.createAt = board.getCreatedAt();
        this.content = board.getContent();
    }

    public static List<AnonymousBoardAdminDto> of(List<AnonymousBoard> boards) {
        return boards.stream().map(AnonymousBoardAdminDto::new).collect(Collectors.toList());
    }
}
