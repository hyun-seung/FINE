package com.fine_server.controller.signup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
public class AuthDto {

    private String university;
    private LocalDateTime updateDateUniver;

    private String phoneNumber;
    private LocalDateTime updateDatePhone;

    private String userResidence;
    private LocalDateTime updateDateResiden;
}
