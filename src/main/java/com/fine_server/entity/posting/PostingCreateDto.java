package com.fine_server.entity.posting;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fine_server.entity.Posting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * written by hyunseung
 * date: 22.05.28
 */

@Getter
@Setter
@NoArgsConstructor
public class PostingCreateDto {

    @NotBlank(message = "제목 X")
    private String title;

    @NotBlank(message = "내용 X")
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    @NotBlank(message = "최대 인원을 설정해주세요")
    private Integer maxMember;

    private Boolean closingCheck;

    /**
     * written by eunhye
     * date: 22.06.15
     */


    public Posting toEntity(){
        return Posting.builder()
                .title(title)
                .content(content)
                .endTime(endTime)
                .maxMember(maxMember)
                .closing_check(closingCheck)
                .build();
    }
}
