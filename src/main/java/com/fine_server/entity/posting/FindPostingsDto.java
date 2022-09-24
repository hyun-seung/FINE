package com.fine_server.entity.posting;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * written by eunhye
 * LastModifiedDate: 22.07.23
 */

@Getter
@NoArgsConstructor
public class FindPostingsDto {

    private Long postingId;
    private Long memberId; // 작성자 아이디

    private String title;
    private String content;
    private int commentCount;

    private Boolean groupCheck; //커뮤니티 구분

    private Integer maxMember;
    private Integer joinCount;
    private Boolean closingCheck;

    private String keyword;


    public FindPostingsDto(Long postingId, Long memberId, String title, Boolean groupCheck, int commentCount,
                           Boolean closingCheck, String keyword) {
        this.postingId = postingId;
        this.memberId = memberId;
        this.title = title;
        this.commentCount = commentCount;
        this.groupCheck = groupCheck;
        this.closingCheck = closingCheck;
        this.keyword = keyword;
    }

    public FindPostingsDto(Long postingId, Long memberId, String title, String content, Boolean groupCheck, int commentCount,
                           Integer maxMember, Integer joinCount, Boolean closingCheck, String keyword) {
        this.postingId = postingId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.groupCheck = groupCheck;
        this.commentCount = commentCount;
        this.maxMember = maxMember;
        this.joinCount = joinCount;
        this.closingCheck = closingCheck;
        this.keyword = keyword;
    }

}