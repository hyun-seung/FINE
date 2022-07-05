package com.fine_server.service.posting;

import com.fine_server.entity.Comment;
import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import com.fine_server.entity.comment.CommentDto;
import com.fine_server.repository.CommentRepository;
import com.fine_server.repository.MemberRepository;
import com.fine_server.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final MemberRepository memberRepository;
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;

    public Comment makeCommnet(CommentDto commentDto) {
        Optional<Member> member = memberRepository.findById(commentDto.getMemberId());
        Optional<Posting> posting = postingRepository.findById(commentDto.getPostingId());
        Comment save = commentRepository.save(commentDto.toEntity());
        save.setMember(member.get());
        save.setPosting(posting.get());
        return save;
    }

    public Comment updateComment(Long commentId, CommentDto commentDto) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        Comment save = comment.get();
        save.updateText(commentDto.getText());
        return save;
    }

    public Long deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getPostingComment(Long postingId) {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            CommentDto commentDto = new CommentDto(
                    comment.getId(), comment.getMember().getId(), comment.getText()
            );
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }



}
