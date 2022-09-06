package com.fine_server.controller.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


/**
 * written by dahae
 * date: 22.06.26
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {

//    level 추가 및 GetMemberDto와 통합
    private String nickname;
    private int userImageNum;
    private String intro;
    private String keyword1;
    private String keyword2;
//    private String keyword3;
    private int follower; //followBack으로 추후 수정
    private String level;


    private String email;
    private LocalDateTime updateDateEmail;
    private String userPhoneNumber;
    private LocalDateTime updateDatePhone;
    private String userResidence;//거주지
    private LocalDateTime updateDateResidence;


    /**
     * added by eunhye
     * date: 22.08.24
     */
    public MemberResponseDto(String nickname, int userImageNum, String intro, String keyword1, String keyword2, String level) {
        this.nickname = nickname;
        this.userImageNum = userImageNum;
        this.intro = intro;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.level = level;
//        this.keyword3 = keyword3;
    }

    public MemberResponseDto(String nickname, int userImageNum, String intro, String keyword1, int follower) {
        this.nickname = nickname;
        this.userImageNum = userImageNum;
        this.intro = intro;
        this.keyword1 = keyword1;
//        this.keyword2 = keyword2;
//        this.keyword3 = keyword3;
        this.follower = follower;
    }
}
