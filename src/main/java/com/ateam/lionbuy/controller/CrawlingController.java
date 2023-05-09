package com.ateam.lionbuy.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ateam.lionbuy.dto.LinkDTO;
import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.ProductLowpriceDTO;
import com.ateam.lionbuy.dto.ProductMallDTO;
import com.ateam.lionbuy.service.CrawlingService;
import com.ateam.lionbuy.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/search")
@RequiredArgsConstructor
@Log4j2
public class CrawlingController {
    
    @Autowired
    private final CrawlingService crawlingService;

    @Autowired
    private final ProductService productService;

    @GetMapping(value = "/info")
    public ResponseEntity<LinkDTO> url_parsing(@RequestParam("url") String url) {
        log.info("-----------" + url + "---------------");
        try {
            Object[] pd_list = crawlingService.getKeyword(url);
            LinkDTO linkDTO = LinkDTO.builder()
            .pdName(String.valueOf(pd_list[0]))
            .price(String.valueOf(pd_list[1]))
            .imageList(pd_list[2])
            .build();
            return ResponseEntity.ok().body(linkDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url Not Found");
        }
    }

    @GetMapping(value = "/product")
    public ResponseEntity<Object> start_crawling(@RequestParam("item") String pdName, @RequestParam("datatype") String datatype) {
        if(datatype.equals("product")) {
            try {
                crawlingService.start_crawling(pdName);
                ProductDTO productData = productService.productData(pdName);
                return ResponseEntity.ok().body(productData);
            } catch (Exception e) {
                return ResponseEntity.ok().body(null);
            }
        }else if(datatype.equals("lowmall")) {
            try {
                ProductMallDTO productlowmallData = productService.lowmallData(pdName);
                return ResponseEntity.ok().body(productlowmallData);
            } catch (Exception e) {
                return ResponseEntity.ok().body(null);
            }
        }else if(datatype.equals("related")) {
            try {
                List<ProductDTO> productrelatedData = productService.relatedData(pdName);
                return ResponseEntity.ok().body(productrelatedData);
            } catch (Exception e) {
                return ResponseEntity.ok().body(null);
            }
        }else if(datatype.equals("lowprice")) {
            try {
                List<ProductLowpriceDTO> productlowpriceData = productService.lowpriceData(pdName);
                return ResponseEntity.ok().body(productlowpriceData);
            } catch (Exception e) {
                return ResponseEntity.ok().body(null);
            }
        }else if(datatype.equals("tag")) {
            try {
                Set<String> producttagData = productService.categoriesData(pdName);
                return ResponseEntity.ok().body(producttagData);
            } catch (Exception e) {
                return ResponseEntity.ok().body(null);
            }
        }else{
            return ResponseEntity.ok().body(null);
        }
    }
}
