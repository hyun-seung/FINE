package com.fine_server.entity.mypage;

import com.fine_server.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


/**
 * written by dahae
 * date: 22.06.23
 * 사용자 이미지 처리와 키워드 처리는 다음 스프린트
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    @NotBlank(message = "닉네임 값은 필수 입력 값입니다.")
    private String nickname;
    private String intro;

    public Member toEntity(){
        return Member.builder()
                .nickname(nickname)
                .intro(intro)
                .build();
    }
}
