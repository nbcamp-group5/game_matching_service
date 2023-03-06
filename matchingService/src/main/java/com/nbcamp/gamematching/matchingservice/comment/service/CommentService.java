package com.nbcamp.gamematching.matchingservice.comment.service;

import com.nbcamp.gamematching.matchingservice.comment.dto.AnonymousCommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.dto.UpdateCommentRequest;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;

import java.util.List;

public interface CommentService {

    void createComment(Long boardId, String content, Member member);

    void createAnonymousComment(Long boardId, String content, Member member);

    void updateComment(Long commentId, UpdateCommentRequest updateCommentRequest,
                       Member member);

    void updateAnonymousComment(Long commentId, UpdateCommentRequest updateCommentRequest,
                                Member member);

    void deleteComment(Long commentId, Member member);

    void deleteAnonymousComment(Long commentId, Member member);

    List<CommentResponse> showComment(Long boardId);

    List<AnonymousCommentResponse> showAnonymousComment(Long boardId);

    CommentResponse getComment(Long commentId);

    AnonymousCommentResponse getAnonymousComment(Long commentId);

    void deleteCommentByAdmin(Long commentId);
}
