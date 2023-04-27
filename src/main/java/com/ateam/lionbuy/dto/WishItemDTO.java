package com.ateam.lionbuy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishItemDTO {

  private Long userNum;
  private String pdName;
  private String imageUrl;
  private String pdLowprice;
  private Long priority;
  private LocalDate choiceDate;

}
