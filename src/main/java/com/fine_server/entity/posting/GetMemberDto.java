package com.fine_server.entity.posting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMemberDto {

    Long memberId;

    String nickname;

//    Integer profileIamgeNum;


    public GetMemberDto(Long memberId, String nickname) {
        this.memberId = memberId;
        this.nickname = nickname;
    }
}
