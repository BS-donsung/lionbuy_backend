package com.ateam.lionbuy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductMallDTO {
    
    private Long pmno;
    private String pdName;
    private String mallName;
    private Long price;
    private LocalDate nowDate;
}
