package com.ateam.lionbuy.controller;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.service.BuyItemService;
import com.ateam.lionbuy.service.CardUseListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/buy")
@RequiredArgsConstructor
public class BuyItemController {

  @Autowired
  private final BuyItemService bService;

  @Autowired
  private final CardUseListService cService;

  @PostMapping(value = "/add")
  public ResponseEntity<String> add_buyList(@RequestBody BuyItemDTO buyItemdto){
    bService.addBuyList(buyItemdto);
    cService.addCardUseList(buyItemdto);
    return ResponseEntity.ok().body("성공");
  }

}
