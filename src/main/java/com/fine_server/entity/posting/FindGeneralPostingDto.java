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
    private Long memeberId;
    private String title;
    private String content;
    private LocalDateTime endTime;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Boolean closingCheck;

    public FindGeneralPostingDto(Long postingId, Long memeberId, String title, String content, LocalDateTime endTime,
                                 LocalDateTime createdDate, LocalDateTime lastModifiedDate, Boolean closingCheck) {
        this.postingId = postingId;
        this.memeberId = memeberId;
        this.title = title;
        this.content = content;
        this.endTime = endTime;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.closingCheck = closingCheck;
    }
}
