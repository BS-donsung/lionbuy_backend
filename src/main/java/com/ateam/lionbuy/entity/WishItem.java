package com.ateam.lionbuy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JoinColumn(name="userNum", referencedColumnName = "userNum")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="pdName", referencedColumnName = "pdName")
    private Product product;

    private Long priority;
    private LocalDate choiceDate;

    @PrePersist
    public void PrePersist() {
        choiceDate = LocalDate.now();
    }

}
