package com.ateam.lionbuy.service;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.Card_use_list;
import com.ateam.lionbuy.repository.BuyItemRepository;
import com.ateam.lionbuy.repository.CardUseListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardUseListServiceImpl implements CardUseListService {

  @Autowired
  private CardUseListRepository cRepository;

  @Override
  public String addCardUseList(BuyItemDTO buyItemDTO) {
    Card_use_list card_use_list = cardUseList_build_entity(buyItemDTO);
    cRepository.save(card_use_list);
    return "성공";
  }
}
