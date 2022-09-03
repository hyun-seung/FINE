package com.fine_server.entity.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSmallInfo {

    private Long memberId;

    private String nickName;

    private int imageNum;

    public MemberSmallInfo(Long memberId, String nickName, int imageNum) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.imageNum = imageNum;
    }
}
