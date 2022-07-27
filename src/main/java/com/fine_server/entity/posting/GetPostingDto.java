package com.fine_server.entity.posting;

import com.fine_server.entity.bookmark.GetBookmarkDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetPostingDto {

    private Long postingId;

    private Long writer_id;

    private String writer_nickname;

    private String title;

    private String content;

    private Boolean closing_check;

    private Boolean group_check;

    private Integer maxMember;

    private Integer headCount;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private List<RecruitingDto> recruitingList;

    private List<CommentMemberDto> comments;

    private List<GetBookmarkDto> bookmarks;


    public GetPostingDto(Long postingId, Long writer_id, String nickname, String title, String content, Boolean closing_check,
                         Boolean group_check, Integer maxMember, Integer headCount,  LocalDateTime createdDate, LocalDateTime lastModifiedDate,
                         List<RecruitingDto> recruitingList, List<CommentMemberDto> comments, List<GetBookmarkDto> bookmarks) {
        this.postingId = id;
        this.writer_id = writer_id;
        this.writer_nickname = nickname;
        this.title = title;
        this.content = content;
        this.closing_check = closing_check;
        this.group_check = group_check;
        this.maxMember = maxMember;
        this.headCount = headCount;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.recruitingList = recruitingList;
        this.comments = comments;
        this.bookmarks = bookmarks;
    }
}
