package com.fine_server.entity.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * written by dahae
 * date: 22.06.26
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {

//    level 추가 및 GetMemberDto와 통합
    private String nickname;
    private int userImageNum;
    private String intro;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private int follower;

    /**
     * added by eunhye
     * date: 22.08.09
     */
//    private int followBack; //맞팔 수
}
