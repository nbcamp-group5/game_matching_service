package com.nbcamp.gamematching.matchingservice.board.dto;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class BoardAdminDto {

    private Long id;
    private String boardImage;
    private String nickname;
    private LocalDateTime createAt;
    private String content;

    public BoardAdminDto(Board board) {
        this.id = board.getId();
        this.boardImage = board.getBoardImage();
        this.nickname = board.getNickname();
        this.createAt = board.getCreatedAt();
        this.content = board.getContent();
    }

    public static List<BoardAdminDto> of(List<Board> boards) {
        return boards.stream().map(BoardAdminDto::new).collect(Collectors.toList());
    }
}
