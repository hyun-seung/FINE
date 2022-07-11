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

    //entity 매개변수로 받아서 하면 컨트롤러에서 dto로 받을 필요 없음. 추후 리팩토링


    private Boolean accept_check;

    public Recruiting toEntity(){

        return Recruiting.builder()
                .accept_check(accept_check)
                .build();
    }
}
