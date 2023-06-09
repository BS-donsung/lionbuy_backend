package com.ateam.lionbuy.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
  public ResponseEntity<String> add_buyList(@RequestBody BuyItemDTO buyItemdto, Authentication auth){
    bService.addBuyList(buyItemdto, String.valueOf(auth.getPrincipal()));
    return ResponseEntity.ok().body("성공");
  }

  @GetMapping(value = "/accountbook")
  public ResponseEntity<List<BuyItemDTO>> get_accountbook(@RequestParam("month") Long month,
                                              @RequestParam("year") Long year,
                                              Authentication auth) {
    try {
      List<BuyItemDTO> buyItemDTOS = bService.getAccountbook(month, year, String.valueOf(auth.getPrincipal()));
      return ResponseEntity.ok().body(buyItemDTOS);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BuyItem Not Found");
    }
  }

  @DeleteMapping(value = "/accountbook")
  public ResponseEntity<String> delete_buyItem(@RequestParam("product") String pdName,
                                               @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate buyDate,
                                               Authentication auth) {
    try {
      bService.deleteBuyItem(pdName, LocalDate.from(buyDate), String.valueOf(auth.getPrincipal()));
      return ResponseEntity.ok().body("성공");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BuyItem Not Found");
    }

  }
}
