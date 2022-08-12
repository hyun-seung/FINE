package com.fine_server.entity.mypage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fine_server.entity.Member;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * written by dahae
 * date: 22.06.23
 * 사용자 이미지 처리와 키워드 처리는 다음 스프린트
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    //@NotBlank(message = "아이디 값은 필수 입력 값입니다.")
    private String userId;
    //@NotBlank
    @Length(min = 8,max = 40)
    private String password;

    private int userImageNum;
    //@NotBlank(message = "닉네임 값은 필수 입력 값입니다.")

    private String nickname; //닉네임 값이 없으면 그냥 id 값으로 넣어주기
    private String intro;
    //private List<String> keyword; //키워드

    private String keyword1;
    private String keyword2;
    private String keyword3;

    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .password(password)
                .nickname(nickname)
                .intro(intro)
                .keyword1(keyword1)
                .keyword2(keyword2)
                .keyword3(keyword3)
                .build();
    }
}
