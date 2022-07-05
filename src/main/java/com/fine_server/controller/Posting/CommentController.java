package com.fine_server.controller.Posting;

import com.fine_server.service.posting.CommentService;
import com.fine_server.entity.Comment;
import com.fine_server.entity.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comment")
    public Comment createComment(@RequestBody CommentDto commentDto) {
        Comment save = commentService.makeCommnet(commentDto);
        return save;
    }

    // 댓글 수정
    @PutMapping("/comment/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        Comment save = commentService.updateComment(commentId, commentDto);
        return save;
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public Long deleteComment(@PathVariable Long commentId) {
        Long deleteCommentId = commentService.deleteComment(commentId);
        return deleteCommentId;
    }
}
