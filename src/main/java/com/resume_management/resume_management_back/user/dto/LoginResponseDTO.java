package com.resume_management.resume_management_back.user.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    
    private int user_id;
    private String name;
    private String email          ;
    private String password       ;
}
