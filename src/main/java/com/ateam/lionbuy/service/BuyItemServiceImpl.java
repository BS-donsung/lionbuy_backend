package com.ateam.lionbuy.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.entity.BuyItem;
import com.ateam.lionbuy.entity.UserInfo;
import com.ateam.lionbuy.repository.BuyItemRepository;
import com.ateam.lionbuy.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BuyItemServiceImpl implements BuyItemService {

  @Autowired
  private BuyItemRepository bRepository;

  @Autowired
  private UserRepository uRepository;

  @Override
  public String addBuyList(BuyItemDTO buyItemDTO) {
    UserInfo userInfo = uRepository.getInfo(buyItemDTO.getUserEmail()).get();
    BuyItem buyItem = buyItem_build_entity(buyItemDTO, userInfo);
    bRepository.save(buyItem);
    return "성공";
  }

  @Override
  public List<BuyItemDTO> getAccountbook(Long month, Long year, String userEmail) {
    List<BuyItem> buy_itemList = bRepository.get_buyitems(month, year, userEmail);
    List<BuyItemDTO> buyitem_dto = new ArrayList<BuyItemDTO>();
    for (BuyItem buyitem : buy_itemList) {
      BuyItemDTO buyItemDTO = buyItem_build_dto(buyitem);
      buyitem_dto.add(buyItemDTO);
    }
    return buyitem_dto;
  }

  @Override
  @Transactional
  public void deleteBuyItem(String pdName, LocalDate buyDate) {
    bRepository.delete_buy(pdName, buyDate);
  }
}

