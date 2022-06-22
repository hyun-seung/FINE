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

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private String endTime;

    private Integer maxMember;
    private Boolean closingCheck;
    private Boolean groupCheck;

    public Posting toEntity() {

        LocalDateTime tempTime;

        if(endTime.equals("")) {    // entTime이 공백으로 올 시 7일 뒤로 자동 생성
            LocalDateTime nowTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            tempTime = nowTime.plusDays(7);
        } else {                    // endTime이 정상적으로 들어올 때
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            tempTime = LocalDateTime.parse(endTime, formatter);
        }

        if(groupCheck.equals(false)) {  // 일반 글로 생성했을 때 maxMember를 2로 설정해준다.
            maxMember = 2;
        }

        return Posting.builder()
                .title(title)
                .content(content)
                .endTime(tempTime)
                .closing_check(false)
                .group_check(groupCheck)
                .maxMember(maxMember)
                .build();
    }
}