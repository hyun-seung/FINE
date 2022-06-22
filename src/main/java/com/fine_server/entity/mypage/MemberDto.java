package com.fine_server.entity.mypage;

import com.fine_server.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


/**
 * written by dahae
 * date: 22.05.27
 * 사용자 이미지처리는 보류
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    @NotBlank(message = "닉네임 값은 필수 입력 값입니다.")
    private String nickname;

    public Member toEntity(){
        return Member.builder()
                .nickname(nickname)
                .build();
    }
}
