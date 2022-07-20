package com.fine_server.entity.posting;

import com.fine_server.entity.Comment;
import com.fine_server.entity.Recruiting;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetPostingDto {

    private Long id;

    private String writer_nickname;

    private String title;

    private String content;

    private Boolean closing_check;

    private Boolean group_check;

    private Integer maxMember;

    private List<Recruiting> recruitingList;

    private List<Comment> comments;

    public GetPostingDto(Long id, String nickname, String title, String content, Boolean closing_check, Boolean group_check, Integer maxMember, List<Recruiting> recruitingList, List<Comment> comments) {
        this.id = id;
        this.writer_nickname = nickname;
        this.title = title;
        this.content = content;
        this.closing_check = closing_check;
        this.group_check = group_check;
        this.maxMember = maxMember;
        this.recruitingList = recruitingList;
        this.comments = comments;
    }
}
