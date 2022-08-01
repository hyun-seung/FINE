package com.fine_server.entity.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fine_server.entity.posting.GetMemberDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentMemberDto {

    Long commentId;

    String text;

    @JsonProperty(value = "member")
    GetMemberDto memberDto;

    public CommentMemberDto(Long commentId, String text, GetMemberDto memberDto) {
        this.commentId = commentId;
        this.text = text;
        this.memberDto = memberDto;
    }
}
