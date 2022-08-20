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

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Integer maxMember;
    private Integer joinCount;
    private Boolean closingCheck;


    public FindPostingsDto(Long postingId, Long memberId, String title, Boolean groupCheck, int commentCount,
                           LocalDateTime createdDate, LocalDateTime lastModifiedDate, Boolean closingCheck) {
        this.postingId = postingId;
        this.memberId = memberId;
        this.title = title;
        this.commentCount = commentCount;
        this.groupCheck = groupCheck;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.closingCheck = closingCheck;
    }

    public FindPostingsDto(Long postingId, Long memberId, String title, String content, Boolean groupCheck, int commentCount,
                           LocalDateTime createdDate, LocalDateTime lastModifiedDate, Integer maxMember, Integer joinCount, Boolean closingCheck) {
        this.postingId = postingId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.groupCheck = groupCheck;
        this.commentCount = commentCount;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.maxMember = maxMember;
        this.joinCount = joinCount;
        this.closingCheck = closingCheck;
    }

}