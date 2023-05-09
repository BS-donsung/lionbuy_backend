package com.ateam.lionbuy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long pno;
    private String pdName;
    private String imageUrl;
    private String pdLowprice;
    private String lowmallurl;
}
