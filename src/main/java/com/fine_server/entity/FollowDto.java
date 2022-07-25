package com.fine_server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * written by eunhye
 * date: 22.07.20
 */


@Getter
@Setter
@NoArgsConstructor
public class FollowDto {

    private Member member;
    private Member friend;

    private Long friendId;
    private String nickname;
    private String intro;
    private String level;

    public Follow toEntity() {
        return Follow.builder()
                .member(member)
                .friend(friend)
                .build();
    }

    public FollowDto(Long friendId, String nickname, String intro, String level) {
        this.friendId = friendId;
        this.nickname = nickname;
        this.intro = intro;
        this.level = level;
    }
}
