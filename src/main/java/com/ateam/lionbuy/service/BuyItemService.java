package com.ateam.lionbuy.service;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.dto.ProductLowpriceDTO;
import com.ateam.lionbuy.dto.UserDTO;
import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.Product_lowprice;
import com.ateam.lionbuy.entity.User_info;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BuyItemService {

  String addBuyList(BuyItemDTO buyItemdto);

  List<BuyItemDTO> getAccountbook(Long month, Long year, String user_email);

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
        .card_brand(buyItemDTO.getCard_brand())
        .card_style(buyItemDTO.getCard_style())
        .build();
    return buy_item;
  }

  default BuyItemDTO buyItem_build_dto(Buy_item buy_item) {
    BuyItemDTO buyItemDTO = BuyItemDTO.builder()
        .bno(buy_item.getBno())
        .user_num(buy_item.getUser_info().getUser_num())
        .pd_name(buy_item.getProduct().getPd_name())
        .pay(String.valueOf(Long.valueOf(buy_item.getPay())))
        .buy_date(buy_item.getBuy_date())
        .card_brand(buy_item.getCard_brand())
        .card_style(buy_item.getCard_style())
        .build();
    return buyItemDTO;
  }
}


