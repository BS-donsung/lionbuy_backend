package com.ateam.lionbuy.service;


import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.dto.UserDTO;
import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.User_info;
import com.ateam.lionbuy.repository.BuyItemRepository;
import com.ateam.lionbuy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyItemServiceImpl implements BuyItemService {

  @Autowired
  private BuyItemRepository bRepository;

  @Autowired
  private UserRepository uRepository;

  @Override
  public String addBuyList(BuyItemDTO buyItemDTO) {
    User_info user_info = uRepository.getInfo(buyItemDTO.getUser_email()).get();
    Buy_item buy_item = buyItem_build_entity(buyItemDTO, user_info);
    bRepository.save(buy_item);
    return "성공";
  }
}

