package com.fine_server.controller.login;

import com.fine_server.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {

    private Long id;

    //수정 필요
    public LoginRes(Member member) {
        id = member.getId();
    }
}
