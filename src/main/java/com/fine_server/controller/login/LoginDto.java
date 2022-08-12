package com.fine_server.controller.login;

import com.fine_server.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


/**
 * written by dahae
 * date: 22.08.03
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String id;
    private String password;

}
