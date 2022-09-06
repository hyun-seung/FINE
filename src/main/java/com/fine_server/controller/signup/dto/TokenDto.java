package com.fine_server.controller.signup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class TokenDto {
    @NotBlank(message = "인증번호를 입력해주세요")
    private String token;

    private LocalDateTime updateDate;
}
