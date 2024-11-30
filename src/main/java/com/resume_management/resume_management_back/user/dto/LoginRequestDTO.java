package com.resume_management.resume_management_back.user.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private int userId;
    private String name;
    private String email          ;
    private String password       ;
    private String phone;
    private String profileImage;
    private String address;
    private String github;
    private String desiredJob;
    private String skill;
    private String title;
    private String content;

}
