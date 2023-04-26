package com.ateam.lionbuy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class BuyItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usernum", referencedColumnName = "usernum")
    private UserInfo userinfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pno", referencedColumnName = "pno")
    private Product product;

    private Long price;
    private LocalDate buydate;
    private String cardbrand;
    private String cardstyle;

    @PrePersist
    public void PrePersist() {
        buydate = LocalDate.now();
    }
}
