package com.fine_server.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

/**
 * written by eunhye
 * date: 22.07.20
 */


@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="friend_id")
    private Member friend;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setFriend(Member friend) {
        this.friend = friend;
    }
}
