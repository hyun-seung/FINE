package com.fine_server.controller.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponseDto {
    @NotBlank(message = "인증번호를 입력해주세요")
    private String token;

    private Long id;
    private String phoneNumber;
    private boolean phoneVerified;
    private LocalDateTime updateDate;
}
