package com.fine_server.entity.posting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMemberDto {

//    MemberResponseDto와 통합

    Long memberId;

    String nickname;

    String level;

    int userImageNum;

//    Integer profileIamgeNum;

    public GetMemberDto(Long memberId, String nickname, String level, int userImageNum) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.level = level;
        this.userImageNum = userImageNum;
    }
}
