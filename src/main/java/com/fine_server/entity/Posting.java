package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

/**
 * written by hyunseung
 * date: 22.05.28
 */

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Posting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    @JsonIgnore
    private Member member;

    @Column(nullable = false)
    private String title;
    private String content;

    private LocalDateTime endTime;

    // 포스팅 작성 시, member와의 관계 설정
    public void setMember(Member member) {
        this.member = member;
//        member.getPostings.add(this);
    }
}
