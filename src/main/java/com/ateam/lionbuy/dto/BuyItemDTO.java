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

  private String user_email;
  private String pd_name;
  private Long price;
  private LocalDateTime buy_date;
  private String card_brand;
  private String card_style;
}
