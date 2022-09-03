package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fine_server.entity.chat.MemberSmallInfo;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

/**
 * written by dahae
 * date: 22.05.27
 *
 * edit by eunhye
 * date: 22.08.13
 */


@Entity
@Getter @Setter //이후 리팩토링 예정
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Member{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String userId;

    @JsonIgnore
    @Column(nullable = false)
    private String password;
    private int userImageNum;

    private String nickname;
    //private String email;
    private String intro; //자기소개

    //private String userPhoneNumber;
    //private String userResidence;//거주지

    @Column(nullable = false)
    @ColumnDefault("1")
    private String level;//프로필 레벨
    private Long report; //신고 당한 횟수

    private String keyword1;
    private String keyword2;
    private String keyword3;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name="memberDetail_id")
    private MemberDetail memberDetail;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer followBack; //맞팔 수

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<ChatMember> chatMemberList = new ArrayList<>();

    @Builder
    public Member(String userID, String password,String nickname, String intro, List<String> keyword){
        this.userId = userID;
        this.password = password;
        this.nickname = nickname;
        this.intro = intro;
    }

    @JsonIgnore
    public MemberSmallInfo getMemberInfo() {
        return new MemberSmallInfo(
                this.id,
                this.nickname,
                this.userImageNum
        );
    }
}
