package com.fine_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

/**
 * written by dahae
 * date: 22.08.20
 */


@Entity
@Getter @Setter //이후 리팩토링 예정
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class MemberDetail extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private LocalDateTime updateDateEmail;
    private String userPhoneNumber;
    private LocalDateTime updateDatePhone;
    private String userResidence;//거주지
    private LocalDateTime updateDateResidence;
}
