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

  void deleteBuyItem(String pdName, LocalDate buyDate, String userEmail);

  default BuyItem buyItem_build_entity(BuyItemDTO buyItemDTO, UserInfo userInfo, Product product) {
    BuyItem buyItem = BuyItem.builder()
        .userinfo(userInfo)
        .product(product)
        .price(buyItemDTO.getPrice())
        .cardbrand(buyItemDTO.getCardBrand())
        .cardstyle(buyItemDTO.getCardStyle())
        .build();
    return buyItem;
  }

  default BuyItemDTO buyItem_build_dto(BuyItem buyItem) {
    BuyItemDTO buyItemDTO = BuyItemDTO.builder()
        .bno(buyItem.getBno())
        .userEmail(buyItem.getUserinfo().getUseremail())
        .pdName(buyItem.getProduct().getPdname())
        .price(buyItem.getPrice())
        .buyDate(buyItem.getBuydate())
        .cardBrand(buyItem.getCardbrand())
        .cardStyle(buyItem.getCardstyle())
        .build();
    return buyItemDTO;
  }
}


