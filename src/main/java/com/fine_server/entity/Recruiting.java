package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

/**
 * written by eunhye
 * date: 22.06.16
 */


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Recruiting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruiting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    @JsonIgnore
    private Posting posting;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    private Boolean accept_check;   // 대기 중(null), 수락(true), 거절(false)

    public void setPosting(Posting posting) {
        this.posting = posting;
        posting.getRecruitingList().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
    }

}