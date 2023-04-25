package com.ateam.lionbuy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNum;
    
    @Column(unique = true)
    private String userEmail;

    private String userPw;
    private String userName;
    private String userGender;
    private String userBirth;
    private LocalDate joinDate;
    
    @PrePersist
    public void PrePersist() {
        joinDate = LocalDate.now();
    }
}
