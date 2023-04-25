package com.ateam.lionbuy.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.WishItemDTO;
import com.ateam.lionbuy.service.ProductService;
import com.ateam.lionbuy.service.WishItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/wishlist")
@RequiredArgsConstructor
public class WishItemController {

  @Autowired
  private final WishItemService wService;

  @Autowired
  private final ProductService pService;

  @PostMapping
  public ResponseEntity<String> add_wishList(@RequestBody WishItemDTO wishItemDTO) {
    wService.addWishList(wishItemDTO);
    return ResponseEntity.ok().body("성공");
  }

  @GetMapping(value = "")
  public ResponseEntity<List<ProductDTO>> getWish(@RequestParam("userEmail") String userEmail) {
    List<ProductDTO> userProductDtoData = wService.userProductList(userEmail);
    return ResponseEntity.ok().body(userProductDtoData);
  }

  @GetMapping(value = "/detail")
  public ResponseEntity<Map<String, Object>> wishDetail(@RequestParam("pdName") String pdName) {
    Map<String, Object> wishProductDetail = pService.getProduct(pdName);
    return ResponseEntity.ok().body(wishProductDetail);
  }

  @DeleteMapping(value = "")
  public ResponseEntity<String> delete_wishItem(@RequestParam("product") String pdName, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDate choiceDate ) {
    wService.deleteBuyItem(pdName, choiceDate);
    return ResponseEntity.ok().body("성공");
  }
}
