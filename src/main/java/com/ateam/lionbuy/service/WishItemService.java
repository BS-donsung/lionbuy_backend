package com.ateam.lionbuy.service;

import java.time.LocalDate;
import java.util.List;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.WishItemDTO;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.UserInfo;
import com.ateam.lionbuy.entity.WishItem;

public interface WishItemService {

  String addWishList(WishItemDTO wishItemdto);

  List<ProductDTO> userProductList(String userEmail);

  void deleteBuyItem(String pdName, LocalDate buyDate);

  default WishItem wishItem_build_entity(Product product, WishItemDTO wishItemDTO) {
    UserInfo userInfo = UserInfo.builder()
        .usernum(wishItemDTO.getUserNum())
        .build();
    WishItem wishItem = WishItem.builder()
        .userinfo(userInfo)
        .product(product)
        .priority(wishItemDTO.getPriority())
        .choicedate(wishItemDTO.getChoiceDate())
        .build();
    return wishItem;
  }

  default ProductDTO product_build_dto(Product product) {
    ProductDTO productDTO = ProductDTO.builder()
        .pno(product.getPno())
        .pdName(product.getPdname())
        .imageUrl(product.getImageurl())
        .pdLowprice(product.getPdlowprice())
        .build();
    return productDTO;
  }
}
