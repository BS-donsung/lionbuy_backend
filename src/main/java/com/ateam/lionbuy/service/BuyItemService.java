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

  default Buy_item buyItem_build_entity(BuyItemDTO buyItemDTO, User_info user_info) {
    Product product = Product.builder()
        .pd_name(buyItemDTO.getPd_name())
        .build();
    Buy_item buy_item = Buy_item.builder()
        .user_info(user_info)
        .product(product)
        .pay(String.valueOf(buyItemDTO.getPrice()))
        .card_brand(buyItemDTO.getCard_brand())
        .card_style(buyItemDTO.getCard_style())
        .build();
    return buy_item;
  }
}


