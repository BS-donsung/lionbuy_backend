package com.ateam.lionbuy.controller;

import com.ateam.lionbuy.dto.BuyItemDTO;
import com.ateam.lionbuy.service.BuyItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
