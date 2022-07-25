package com.fine_server.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

/**
 * written by dahae
 * date: 22.06.26
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
}
