package com.fine_server.entity.posting;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * written by hyunseung
 * date: 22.05.28
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

    public FindAllPostingDto(Long postingId, Long memberId, String title, String content, LocalDateTime endTime,
                             LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.postingId = postingId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.endTime = endTime;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
