package com.fine_server.entity.mypage;

import com.fine_server.entity.Member;
import lombok.*;

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

    @NotBlank(message = "닉네임 값은 필수 입력 값입니다.")
    private String nickname;
    private String intro;
    private List<String> keyword; //키워드

    public Member toEntity(){
        return Member.builder()
                .nickname(nickname)
                .intro(intro)
                .keyword(keyword)
                .build();
    }
}
