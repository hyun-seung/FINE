package com.fine_server.entity.posting;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.07.22
 * LastModifiedPerson : eunhye
 */

@Getter
@NoArgsConstructor
public class FindGroupPostingDto {

    private Long postingId;

    private Long memberId;
    private String nickname;

    private String title;
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Integer maxMember;
    private Integer joinCount;
    private Boolean closingCheck;

    public FindGroupPostingDto(Long postingId, Long memberId, String nickname, String title, String content,
                               LocalDateTime createdDate, LocalDateTime lastModifiedDate, Integer maxMember, Integer joinCount, Boolean closingCheck) {
        this.postingId = postingId;
        this.memberId = memberId;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.maxMember = maxMember;
        this.joinCount = joinCount;
        this.closingCheck = closingCheck;
    }
}
