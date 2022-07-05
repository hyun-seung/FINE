package com.fine_server.entity.posting;

import com.fine_server.entity.Group;
import com.fine_server.entity.Member;
import com.fine_server.entity.Posting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupCreateDto {

    private Posting posting;
    private Member member;

    public GroupCreateDto(Posting posting, Member member) {
        this.posting = posting;
        this.member = member;
    }

    public Group toEntity() {
        return Group.builder()
                .posting(posting)
                .member(member)
                .build();
    }
}
