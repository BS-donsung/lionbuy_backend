package com.ateam.lionbuy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.entity.BuyItem;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.UserInfo;

public interface BuyItemService {

  String addBuyList(BuyItemDTO buyItemdto);

  List<BuyItemDTO> getAccountbook(Long month, Long year, String userEmail);

  void deleteBuyItem(String pdName, LocalDate buyDate);

  default BuyItem buyItem_build_entity(BuyItemDTO buyItemDTO, UserInfo userInfo) {
    Product product = Product.builder()
        .pdName(buyItemDTO.getPdName())
        .build();
    BuyItem buyItem = BuyItem.builder()
        .userInfo(userInfo)
        .product(product)
        .price(buyItemDTO.getPrice())
        .cardBrand(buyItemDTO.getCardBrand())
        .cardStyle(buyItemDTO.getCardStyle())
        .build();
    return buyItem;
  }

  default BuyItemDTO buyItem_build_dto(BuyItem buyItem) {
    BuyItemDTO buyItemDTO = BuyItemDTO.builder()
        .pdName(buyItem.getProduct().getPdName())
        .price(buyItem.getPrice())
        .buyDate(buyItem.getBuyDate())
        .cardBrand(buyItem.getCardBrand())
        .cardStyle(buyItem.getCardStyle())
        .build();
    return buyItemDTO;
  }
}


