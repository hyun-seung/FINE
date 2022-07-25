package com.fine_server.entity.posting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fine_server.entity.Recruiting;
import lombok.AllArgsConstructor;
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

    private Boolean accept_check;

    @JsonProperty(value = "member")
    GetMemberDto memberDto;

    public Recruiting toEntity(){

        return Recruiting.builder()
                .accept_check(accept_check)
                .build();
    }

    public RecruitingDto(Boolean accept_check, GetMemberDto memberDto) {
        this.accept_check = accept_check;
        this.memberDto = memberDto;
    }
}
