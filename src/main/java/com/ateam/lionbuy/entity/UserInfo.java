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

    public UserInfo(Long usernum, String username, String useremail, String userpw) {
        this.usernum = usernum;
        this.username = username;
        this.useremail = useremail;
        this.userpw = userpw;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long usernum;
    
    @Column(unique = true)
    public String useremail;

    public String userpw;
    public String username;
    public String usergender;
    public String userbirth;
    public LocalDate joindate;
    
    @PrePersist
    public void PrePersist() {
        joindate = LocalDate.now();
    }
}
