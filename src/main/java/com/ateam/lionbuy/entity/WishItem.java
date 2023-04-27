package com.ateam.lionbuy.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
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
public class WishItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usernum", referencedColumnName = "usernum")
    private UserInfo userinfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pno", referencedColumnName = "pno")
    private Product product;

    private Long priority;
    private LocalDate choicedate;

    @PrePersist
    public void PrePersist() {
        choicedate = LocalDate.now();
    }

}
