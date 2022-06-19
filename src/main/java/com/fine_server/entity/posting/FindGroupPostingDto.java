package com.fine_server.entity.posting;

import java.time.LocalDateTime;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.20
 * LastModifiedPerson : hyunseung
 */

public class FindGroupPostingDto {

    /*
        메모장 : 일단 내가 생각하는 그룹 포스팅에 필요한 것들을 넣어놨어. 확인해보고 변경할 게 있으면 변경하면 돼!
     */

    private Long postingId;
    private Long memberId;
    private String title;
    private String content;
    private LocalDateTime endTime;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    private Integer maxMember;
    private Boolean closingCheck;

    public FindGroupPostingDto(Long postingId, Long memberId, String title, String content, LocalDateTime endTime,
                               LocalDateTime createdDate, LocalDateTime lastModifiedDate, Integer maxMember, Boolean closingCheck) {
        this.postingId = postingId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.endTime = endTime;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.maxMember = maxMember;
        this.closingCheck = closingCheck;
    }
}
