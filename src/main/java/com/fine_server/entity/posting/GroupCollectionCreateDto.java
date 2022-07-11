package com.fine_server.entity.posting;

import com.fine_server.entity.Group;
import com.fine_server.entity.GroupCollection;
import com.fine_server.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupCollectionCreateDto {

    private Group group;
    private Member member;

    public GroupCollectionCreateDto(Group group, Member member) {
        this.group = group;
        this.member = member;
    }

    public GroupCollection toEntity() {
        return GroupCollection.builder()
                .group(group)
                .member(member)
                .build();
    }
}
