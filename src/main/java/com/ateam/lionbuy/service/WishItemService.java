package com.ateam.lionbuy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

  default WishItem wishItem_build_entity(WishItemDTO wishItemDTO) {
    UserInfo userInfo = UserInfo.builder()
        .userNum(wishItemDTO.getUserNum())
        .build();
    Product product = Product.builder()
        .pdName(wishItemDTO.getPdName())
        .build();
    WishItem wishItem = WishItem.builder()
        .userInfo(userInfo)
        .product(product)
        .priority(wishItemDTO.getPriority())
        .choiceDate(wishItemDTO.getChoiceDate())
        .build();
    return wishItem;
  }

  default ProductDTO product_build_dto(Product product) {
    ProductDTO productDTO = ProductDTO.builder()
      .pdName(product.getPdName())
      .imageUrl(product.getImageUrl())
      .pdLowprice(product.getPdLowprice())
      .build();
    return productDTO;
  }
}
