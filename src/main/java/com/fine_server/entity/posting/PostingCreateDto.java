package com.fine_server.entity.posting;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fine_server.entity.Posting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.20
 * LastModifiedPerson : hyunseung
 */

@Getter
@Setter
@NoArgsConstructor
public class PostingCreateDto {

    @NotBlank(message = "제목 X")
    private String title;

    @NotBlank(message = "내용 X")
    private String content;

    private Integer maxMember;
    private Boolean closingCheck;
    private Boolean groupCheck;

    public Posting toEntity() {

        if(groupCheck.equals(false)) {  // 일반 글로 생성했을 때 maxMember를 2로 설정해준다.
            maxMember = 2;
        }

        return Posting.builder()
                .title(title)
                .content(content)
                .closing_check(false)
                .group_check(groupCheck)
                .maxMember(maxMember)
                .build();
    }
}