package com.fmartinez.disney.app.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoginRegisterRequest {
    private String email;
    private String password;
}
