package com.ateam.lionbuy.dto;

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
public class WishItemDTO {

  private Long user_num;
  private String pd_name;
  private String image_url;
  private String pd_lowprice;
  private Long priority;
  private LocalDateTime choice_date;

}
