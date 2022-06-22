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
public class FindAllPostingDto {

    private Long postingId;
    private Long memberId;
    private String title;
    private String content;
    private LocalDateTime endTime;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Integer maxMember;
    private Boolean closingCheck;
    private Boolean groupCheck;

    public FindAllPostingDto(Long postingId, Long memberId, String title, String content, LocalDateTime endTime,
                             LocalDateTime createdDate, LocalDateTime lastModifiedDate,
                             Integer maxMember, Boolean closingCheck, Boolean groupCheck) {
        this.postingId = postingId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.endTime = endTime;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.maxMember = maxMember;
        this.closingCheck = closingCheck;
        this.groupCheck = groupCheck;
    }
}
