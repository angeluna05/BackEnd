package com.example.ytalentbackend.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRecoveryRequest {
    private String correo;
    private Integer userId;
    private String code;
    private String newPassword;

}
