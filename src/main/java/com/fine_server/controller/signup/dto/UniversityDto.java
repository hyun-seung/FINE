package com.fine_server.controller.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDto {
    @NotBlank(message = "인증번호를 입력해주세요")
    private String token;

    private String university;
    private LocalDateTime updateDate;
}
