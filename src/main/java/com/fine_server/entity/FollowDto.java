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

    //getMemberDto 사용해서 리팩토링 (getMemberDto에는 intro 없으므로 이를 논의 후 추가)
    public FollowDto(Long friendId, String nickname, String intro, String level) {
        this.friendId = friendId;
        this.nickname = nickname;
        this.intro = intro;
        this.level = level;
    }
}
