package com.fine_server.entity.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * written by hyunseung
 * LastModifiedDate: 22.06.26
 * LastModifiedPerson : hyunseung
 */

@Getter
@Setter
public class TextDto {
    @NotBlank(message = "텍스트 X")
    private String text;

}
