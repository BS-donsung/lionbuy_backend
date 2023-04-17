package com.ateam.lionbuy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.WishItemDTO;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.Wish_item;
import com.ateam.lionbuy.repository.UserRepository;
import com.ateam.lionbuy.repository.WishItemRepository;

import jakarta.transaction.Transactional;

@Service
public class WishItemServiceImpl implements WishItemService {

  @Autowired
  private WishItemRepository wRepository;

  @Autowired
  private UserRepository uRepository;

  @Override
  public String addWishList(WishItemDTO wishItemDTO) {
    Wish_item wish_item = wishItem_build_entity(wishItemDTO);
    wRepository.save(wish_item);
    return "성공";
  }

  @Override
  public List<ProductDTO> userProductList(String user_email) {
    List<Product> userProductEntityData = wRepository.wish_product(user_email).get();
    List<ProductDTO> userProductDtoData = new ArrayList<ProductDTO>();
    for (int i = 0; i < userProductEntityData.size(); i++) {
      userProductDtoData.add(product_build_dto(userProductEntityData.get(i)));
    }
    return userProductDtoData;
  }

  @Override
  @Transactional
  public void deleteBuyItem(String pd_name, LocalDateTime choice_date) {
    wRepository.delete_wish(pd_name, choice_date);
  }
}
