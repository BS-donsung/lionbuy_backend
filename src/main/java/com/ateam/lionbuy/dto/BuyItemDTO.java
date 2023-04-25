package com.ateam.lionbuy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyItemDTO {

  private String userEmail;
  private String pdName;
  private Long price;
  private LocalDate buyDate;
  private String cardBrand;
  private String cardStyle;
}
