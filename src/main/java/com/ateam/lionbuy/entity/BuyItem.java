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
    @JoinColumn(name="userNum", referencedColumnName = "userNum")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="pdName", referencedColumnName = "pdName")
    private Product product;

    private Long price;
    private LocalDate buyDate;
    private String cardBrand;
    private String cardStyle;

    @PrePersist
    public void PrePersist() {
        buyDate = LocalDate.now();
    }
}
