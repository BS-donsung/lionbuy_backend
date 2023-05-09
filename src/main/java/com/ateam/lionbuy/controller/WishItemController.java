package com.ateam.lionbuy.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.ProductLowpriceDTO;
import com.ateam.lionbuy.dto.ProductMallDTO;
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
  private final ProductService productService;

  @PostMapping
  public ResponseEntity<String> add_wishList(Authentication auth, @RequestBody WishItemDTO wishItemDTO) {
    try {
      wService.addWishList(wishItemDTO, String.valueOf(auth.getPrincipal()));
      return ResponseEntity.ok().body("성공");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WishItem Not Found");
    }
  }

  @GetMapping(value = "")
  public ResponseEntity<List<ProductDTO>> getWish(Authentication auth) {
    List<ProductDTO> userProductDtoData = wService.userProductList(String.valueOf(auth.getPrincipal()));
    return ResponseEntity.ok().body(userProductDtoData);
  }

  @GetMapping(value = "/detail")
  public ResponseEntity<Object> wishDetail(@RequestParam("item") String pdName, @RequestParam("datatype") String datatype) {
    if(datatype.equals("product")) {
      try {
          ProductDTO productData = productService.productData(pdName);
          return ResponseEntity.ok().body(productData);
      } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WishDetail Not Found");
      }
    }else if(datatype.equals("lowmall")) {
        try {
            ProductMallDTO productlowmallData = productService.lowmallData(pdName);
            return ResponseEntity.ok().body(productlowmallData);
        } catch (Exception e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WishDetail Not Found");
        }
    }else if(datatype.equals("related")) {
        try {
            List<ProductDTO> productrelatedData = productService.relatedData(pdName);
            return ResponseEntity.ok().body(productrelatedData);
        } catch (Exception e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WishDetail Not Found");
        }
    }else if(datatype.equals("lowprice")) {
        try {
            List<ProductLowpriceDTO> productlowpriceData = productService.lowpriceData(pdName);
            return ResponseEntity.ok().body(productlowpriceData);
        } catch (Exception e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WishDetail Not Found");
        }
    }else if(datatype.equals("tag")) {
        try {
            Set<String> producttagData = productService.categoriesData(pdName);
            return ResponseEntity.ok().body(producttagData);
        } catch (Exception e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WishDetail Not Found");
        }
    }else{
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WishDetail Not Found");
    }
  }

  @DeleteMapping(value = "")
  public ResponseEntity<String> delete_wishItem(@RequestParam("product") String pdName,
                                                @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate choiceDate,
                                                Authentication auth) {
    wService.deleteWishItem(pdName, choiceDate, String.valueOf(auth.getPrincipal()));
    return ResponseEntity.ok().body("성공");
  }
}
