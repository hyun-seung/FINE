package com.fine_server.entity.posting;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.20
 * LastModifiedPerson : hyunseung
 */

@Getter
@NoArgsConstructor
public class FindGeneralPostingDto {

    private Long postingId;

    private String nickname;    // 작성자의 닉네임

    private String title;
    private String content;
    private int commentCount;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Boolean closingCheck;

    public FindGeneralPostingDto(Long postingId, String nickname,
                                 String title, String content, int commnetCount,
                                 LocalDateTime createdDate, LocalDateTime lastModifiedDate, Boolean closingCheck) {
        this.postingId = postingId;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.commentCount = commnetCount;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.closingCheck = closingCheck;
    }
}