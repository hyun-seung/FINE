package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

/**
 * written by hyunseung , eunhye
 * LastModifiedDate: 22.06.20
 * LastModifiedPerson : eunhye
 */

@Entity
@Getter
@Builder
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Posting extends BaseEntity {

    /*
        메모장 : endTime과 closingCheck를 저장한다. -> endTime이 되면 closingCheck를 변경한다. how? (자동으로는 변경이 되지 않는다.)
                ㄴ 이에 대한 생각 : 1. endTime을 제거한다. 글쓴이가 마감 클릭을 하기 전까지 그 글은 마감이 되지 않는거다.
                                 2. 해당 글 조회(GET : /posting/{postingId}를 할 때 현재 시간과 endTime을 변경하여 현재 시간이 endTime을 넘겼다면
                                    closingCheck를 변경하고 이에 대한 정보를 조회 요청한 유저에게 뿌린다.
                                    (즉, 이 유저는 목록에서는 마감이 되지 않은 글을 보고 눌렀다가 들어가보니 마감이 된 거다.)
                                 3. 가장 많이 일어나는 이벤트인 전체 글 조회(GET : /posting)을 할 때 각각의 시간 초과 여부를 확인하고 변경한 후 리스트를 뿌린다.
                                    (데이터가 많아졌을 때 발생하는 연산 처리량이 어마무시하다... but 이게 그나마 마감 여부 변경 처리를 빈번하게 할 수 있는 방법이다.)
     */

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

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ColumnDefault("false")
    private Boolean closing_check;  // false -> 마감 X , true -> 마감

    @ColumnDefault("false")
    private Boolean group_check;    // false -> 일반 글(General) , true -> 단체 글(Group)

    private Integer maxMember;

    // 포스팅 작성 시, member와의 관계 설정
    public void setMember(Member member) {
        this.member = member;
//        member.getPostings.add(this);
    }
}