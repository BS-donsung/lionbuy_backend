package com.ateam.lionbuy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    private String userEmail;
    private String userPw;
    private String userName;
    private String userGender;
    private String userBirth;
    private LocalDate joinDate;
}
