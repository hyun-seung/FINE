package com.fine_server.controller.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String nickname;
    private int userImageNum;
    private String intro;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private int level;
}
