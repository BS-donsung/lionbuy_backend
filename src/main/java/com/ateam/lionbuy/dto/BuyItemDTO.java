package com.ateam.lionbuy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyItemDTO {

  private Long bno;
  private Long user_num;
  private String pd_name;
  private String pay;
  private LocalDateTime buy_date;
  private String card_brand;
  private String card_style;
}
