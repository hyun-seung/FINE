package com.fine_server.entity.mypage;

import com.fine_server.entity.Keyword;
import com.fine_server.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class KeywordDto {

    private Member member;
    private String keyword;

    public Keyword toEntity() {
        return Keyword.builder()
                .member(member)
                .keyword(keyword)
                .build();
    }

}
