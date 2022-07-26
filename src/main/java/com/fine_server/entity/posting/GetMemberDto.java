package com.fine_server.entity.posting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMemberDto {

    Long memberId;

    String nickname;

    String level;

//    Integer profileIamgeNum;

    public GetMemberDto(Long memberId, String nickname, String level) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.level = level;
    }
}
