package com.fine_server.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

/**
 * written by eunhye
 * date: 22.08.28
 */

@Builder
@Entity @Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Keyword {
    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_Id")
    private Member member;

    private String keyword;

    // 거주지 1 학교 2 전공 3
    private Integer type;
}
