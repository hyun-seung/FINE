package com.fine_server.controller.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponseDto {
    private Long id;
    private String phoneNumber;
    private boolean phoneVerified;
    private LocalDateTime updateDate;
}
