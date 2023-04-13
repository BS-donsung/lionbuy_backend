package com.ateam.lionbuy.service;

import java.util.List;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.WishItemDTO;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.User_info;
import com.ateam.lionbuy.entity.Wish_item;

public interface WishItemService {

  String addWishList(WishItemDTO wishItemdto);

  List<ProductDTO> userProductList(String user_email);

  default Wish_item wishItem_build_entity(WishItemDTO wishItemDTO) {
    User_info user_info = User_info.builder()
        .user_num(wishItemDTO.getUser_num())
        .build();
    Product product = Product.builder()
        .pd_name(wishItemDTO.getPd_name())
        .build();
    Wish_item wish_item = Wish_item.builder()
        .user_info(user_info)
        .product(product)
        .priority(wishItemDTO.getPriority())
        .choice_date(wishItemDTO.getChoice_date())
        .build();
    return wish_item;
  }

  default ProductDTO product_build_dto(Product product) {
    ProductDTO productDTO = ProductDTO.builder()
      .pd_name(product.getPd_name())
      .image_url(product.getImage_url())
      .pd_lowprice(product.getPd_lowprice())
      .build();
    return productDTO;
  }
}
