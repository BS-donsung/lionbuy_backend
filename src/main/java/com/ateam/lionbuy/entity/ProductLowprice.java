package com.ateam.lionbuy.entity;

import java.time.LocalDate;
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
public class ProductLowprice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pno", referencedColumnName = "pno")
    private Product product;

    private String pdlowprice;
    private LocalDate nowdate;

    @PrePersist
    public void PrePersist() {
        nowdate = LocalDate.now();
    }
}
