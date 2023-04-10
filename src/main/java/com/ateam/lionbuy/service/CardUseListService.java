package com.ateam.lionbuy.service;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.entity.*;

public interface CardUseListService {

  String addCardUseList(BuyItemDTO buyItemdto);

  default Card_use_list cardUseList_build_entity(BuyItemDTO buyItemdto) {
    Buy_item buy_item = Buy_item.builder()
        .bno(buyItemdto.getBno())
        .build();
    Card_use_list card_use_list = Card_use_list.builder()
        .buy_list(buy_item)
        .card_brand(buyItemdto.getCard_brand())
        .card_style(buyItemdto.getCard_style())
        .build();
    return card_use_list;
  }
}
