package com.ateam.lionbuy.controller;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.dto.WishItemDTO;
import com.ateam.lionbuy.service.CrawlingService;
import com.ateam.lionbuy.service.WishItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wish")
@RequiredArgsConstructor
public class WishItemController {

  @Autowired
  private final WishItemService wService;

  @PostMapping(value = "/add")
  public ResponseEntity<String> add_wishList(@RequestBody WishItemDTO wishItemDTO) {
    wService.addWishList(wishItemDTO);
    return ResponseEntity.ok().body("성공");
  }
}
