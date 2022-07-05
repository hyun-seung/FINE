package com.fine_server.entity.posting;

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
@AllArgsConstructor
public class RecruitingDto {


    private Boolean accept_check;


    public Recruiting toEntity(){

        return Recruiting.builder()
                .accept_check(accept_check)
                .build();
    }
}
