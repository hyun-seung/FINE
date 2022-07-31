package com.fine_server.entity.posting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fine_server.entity.Recruiting;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * written by eunhye
 * date: 22.06.28
 */

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class RecruitingDto {

    private Long recruitingId;
    private Boolean acceptCheck;

    @JsonProperty(value = "member")
    GetMemberDto memberDto;

    public Recruiting toEntity(){

        return Recruiting.builder()
                .accept_check(acceptCheck)
                .build();
    }

    public RecruitingDto(Long recruitingId, Boolean acceptCheck, GetMemberDto memberDto) {
        this.recruitingId = recruitingId;
        this.acceptCheck = acceptCheck;
        this.memberDto = memberDto;
    }
}
