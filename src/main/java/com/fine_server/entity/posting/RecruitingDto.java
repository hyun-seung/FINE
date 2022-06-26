package com.fine_server.entity.posting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import com.fine_server.entity.Recruiting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;


/**
 * written by dahae
 * date: 22.06.23
 * 사용자 이미지 처리와 키워드 처리는 다음 스프린트
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitingDto {


    private Posting posting;

    private Member member;

    private Boolean accept_check;


    public Recruiting toEntity(){
        return Recruiting.builder()
                .posting(posting)
                .member(member)
                .accept_check(accept_check)
                .build();
    }
}
