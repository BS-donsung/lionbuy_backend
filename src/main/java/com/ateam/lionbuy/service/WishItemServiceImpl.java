package com.ateam.lionbuy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.WishItemDTO;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.WishItem;
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
    WishItem wishItem = wishItem_build_entity(wishItemDTO);
    wRepository.save(wishItem);
    return "성공";
  }

  @Override
  public List<ProductDTO> userProductList(String userEmail) {
    List<Product> userProductEntityData = wRepository.wish_product(userEmail).get();
    List<ProductDTO> userProductDtoData = new ArrayList<ProductDTO>();
    for (int i = 0; i < userProductEntityData.size(); i++) {
      userProductDtoData.add(product_build_dto(userProductEntityData.get(i)));
    }
    return userProductDtoData;
  }

  @Override
  @Transactional
  public void deleteBuyItem(String pdName, LocalDate choiceDate) {
    wRepository.delete_wish(pdName, choiceDate);
  }
}
