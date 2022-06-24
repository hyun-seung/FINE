package com.fine_server.entity.comment;

import com.fine_server.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentDto {

    private Long memberId;

    private Long postingId;

    @NotBlank(message = "글 X")
    private String text;

    public CommentDto(Long memberId, Long postingId, String text) {
        this.memberId = memberId;
        this.postingId = postingId;
        this.text = text;
    }

    public Comment toEntity() {
        return Comment.builder()
                .text(text)
                .build();
    }
}
