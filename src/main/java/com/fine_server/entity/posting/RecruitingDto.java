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
 * written by eunhye
 * date: 22.06.28
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitingDto {


    private Boolean accept_check;


    public Recruiting toEntity(){

        return Recruiting.builder()
                .accept_check(accept_check)
                .build();
    }
}
