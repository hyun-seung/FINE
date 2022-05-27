package com.fine_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

/**
 * written by dahae
 * date: 22.05.27
 * 사용자 이미지처리는 보류
 */

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String nickname;
    private String email;
    private String password;
    private String userIntroduction;

    private String userUniversity;//대학명
    private String userCollege;//단과대

    private String userPhoneNumber;
    private String userResidence;//거주지

    private String level;//프로필 레벨
    private Long report; //신고 당한 횟수

}
