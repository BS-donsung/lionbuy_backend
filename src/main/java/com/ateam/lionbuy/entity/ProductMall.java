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
public class ProductMall {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pmno;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="pdName", referencedColumnName = "pdName")
    private Product product;

    private String mallName;
    private Long price;
    private LocalDate nowDate;

    @PrePersist
    public void PrePersist() {
        nowDate = LocalDate.now();
    }
}
