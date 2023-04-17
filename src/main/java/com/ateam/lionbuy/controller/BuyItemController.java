package com.ateam.lionbuy.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.service.BuyItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/buy")
@RequiredArgsConstructor
public class BuyItemController {

  @Autowired
  private final BuyItemService bService;

  @PostMapping(value = "/add")
  public ResponseEntity<String> add_buyList(@RequestBody BuyItemDTO buyItemdto){
    bService.addBuyList(buyItemdto);
    return ResponseEntity.ok().body("성공");
  }

  @GetMapping(value = "/accountbook")
  public ResponseEntity<List<BuyItemDTO>> get_accountbook(@RequestParam("month") Long month,
                                              @RequestParam("year") Long year,
                                              @RequestParam("user_email") String user_email) {
    List<BuyItemDTO> buyItemDTOS = bService.getAccountbook(month, year, user_email);
    return ResponseEntity.ok().body(buyItemDTOS);
  }

  @DeleteMapping(value = "/accountbook")
  public ResponseEntity<String> delete_buyItem(@RequestParam("product") String pd_name, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") LocalDateTime buy_date ) {
    bService.deleteBuyItem(pd_name, buy_date);
    return ResponseEntity.ok().body("성공");
  }


}
