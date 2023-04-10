package com.ateam.lionbuy.service;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.dto.UserDTO;
import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.Product_lowprice;
import com.ateam.lionbuy.entity.User_info;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.Map;

public interface BuyItemService {

  String addBuyList(BuyItemDTO buyItemdto);

  default Buy_item buyItem_build_entity(BuyItemDTO buyItemDTO) {
    User_info user_info = User_info.builder()
        .user_num(buyItemDTO.getUser_num())
        .build();
    Product product = Product.builder()
        .pd_name(buyItemDTO.getPd_name())
        .build();
    Buy_item buy_item = Buy_item.builder()
        .user_info(user_info)
        .product(product)
        .pay(buyItemDTO.getPay())
        .buy_date(buyItemDTO.getBuy_date())
        .build();
    return buy_item;
  }
}


