package com.ateam.lionbuy.service;

import java.util.List;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.User_info;

public interface BuyItemService {

  String addBuyList(BuyItemDTO buyItemdto);

  List<BuyItemDTO> getAccountbook(Long month, Long year, String user_email);

  default Buy_item buyItem_build_entity(BuyItemDTO buyItemDTO, User_info user_info) {
    Product product = Product.builder()
        .pd_name(buyItemDTO.getPd_name())
        .build();
    Buy_item buy_item = Buy_item.builder()
        .user_info(user_info)
        .product(product)
        .price(buyItemDTO.getPrice())
        .card_brand(buyItemDTO.getCard_brand())
        .card_style(buyItemDTO.getCard_style())
        .build();
    return buy_item;
  }

  default BuyItemDTO buyItem_build_dto(Buy_item buy_item) {
    BuyItemDTO buyItemDTO = BuyItemDTO.builder()
        .pd_name(buy_item.getProduct().getPd_name())
        .price(buy_item.getPrice())
        .buy_date(buy_item.getBuy_date())
        .card_brand(buy_item.getCard_brand())
        .card_style(buy_item.getCard_style())
        .build();
    return buyItemDTO;
  }
}


