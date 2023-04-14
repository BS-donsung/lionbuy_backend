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
public class ProductMallDTO {
    
    private String pd_name;
    private String mall_name;
    private Long price;
    private LocalDateTime now_date;
}
