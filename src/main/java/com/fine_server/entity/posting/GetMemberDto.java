package com.fine_server.entity.posting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMemberDto {

    private Long memberId;
    private String nickname;

    private int userImageNum;

    private String intro;
    private String keyword1;
    private String keyword2;
    //    private String keyword3;
    private int follower; //followBack으로 추후 수정
    private String level;

    public GetMemberDto(Long memberId, String nickname, String level, int userImageNum) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.level = level;
        this.userImageNum = userImageNum;
    }

    public GetMemberDto(Long memberId, String nickname, int userImageNum, String intro, String keyword1, String keyword2, String level) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.userImageNum = userImageNum;
        this.intro = intro;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.level = level;
    }
}
