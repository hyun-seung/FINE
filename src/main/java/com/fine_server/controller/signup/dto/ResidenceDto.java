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
public class ResidenceDto {
    private String userResidence;
    private LocalDateTime updateDate;
}
