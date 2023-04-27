package com.ateam.lionbuy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usernum;
    
    @Column(unique = true)
    private String useremail;

    private String userpw;
    private String username;
    private String usergender;
    private String userbirth;
    private LocalDate joindate;
    
    @PrePersist
    public void PrePersist() {
        joindate = LocalDate.now();
    }
}
