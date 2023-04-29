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
public class ProductLowpriceDTO {
    
    private Long plno;
    private String pdName;
    private Long pdLowprice;
    private LocalDate nowDate;
}
