package com.ateam.lionbuy.dto;

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
    
    private String pd_name;
    private Long pd_lowprice;
    private LocalDateTime now_date;
}
