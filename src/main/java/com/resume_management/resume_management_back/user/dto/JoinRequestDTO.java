package com.resume_management.resume_management_back.user.dto;

import lombok.Data;

@Data
public class JoinRequestDTO {
  
  private String email          ;
  private String password       ;
  private String name           ;

}
