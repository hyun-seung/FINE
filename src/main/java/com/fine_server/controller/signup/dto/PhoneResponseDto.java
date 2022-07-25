package com.fine_server.controller.signup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneResponseDto {
    private Long id;
    private String phoneNumber;
    private boolean phoneVerified;
}
