package com.ateam.lionbuy.dto;

import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.User_info;
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
  private Long cno;
  private Buy_item buy_list;
  private String card_brand;
  private String card_style;
}
