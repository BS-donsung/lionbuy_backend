package com.ateam.lionbuy.service;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.dto.WishItemDTO;
import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.Wish_item;
import com.ateam.lionbuy.repository.BuyItemRepository;
import com.ateam.lionbuy.repository.WishItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishItemServiceImpl implements WishItemService {

  @Autowired
  private WishItemRepository wRepository;

  @Override
  public String addWishList(WishItemDTO wishItemDTO) {
    Wish_item wish_item = wishItem_build_entity(wishItemDTO);
    wRepository.save(wish_item);
    return "성공";
  }
}
