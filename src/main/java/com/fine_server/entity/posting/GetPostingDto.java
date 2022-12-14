package com.fine_server.entity.posting;

import com.fine_server.entity.comment.CommentMemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetPostingDto {

    private Long postingId;

    private Long writerId;
    private String writerNickname;
    private int userImageNum;
    private String title;
    private String content;
    private Boolean closingCheck;
    private Boolean groupCheck;
    private Integer maxMember;

    private String keyword;

    private Integer joinCount;
    private Long checkRecruitingId;
    private Long checkBookmarkId;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private List<RecruitingDto> recruitingList;
    private List<CommentMemberDto> comments;
//    private List<GetBookmarkDto> bookmarks;

    public GetPostingDto(Long postingId, Long writerId, String writerNickname, int userImageNum, String title, String content, Boolean closingCheck,
                         Boolean groupCheck, Integer maxMember, String keyword, Integer joinCount, Long recruitingId, Long bookmarkId,
                         LocalDateTime createdDate, LocalDateTime lastModifiedDate,
                         List<RecruitingDto> recruitingList, List<CommentMemberDto> comments) {
        this.postingId = postingId;
        this.writerId = writerId;
        this.writerNickname = writerNickname;
        this.userImageNum = userImageNum;

        this.title = title;
        this.content = content;
        this.closingCheck = closingCheck;
        this.groupCheck = groupCheck;
        this.maxMember = maxMember;

        this.keyword = keyword;

        this.joinCount = joinCount;
        this.checkRecruitingId = recruitingId;
        this.checkBookmarkId = bookmarkId;

        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.recruitingList = recruitingList;
        this.comments = comments;
    }
}
