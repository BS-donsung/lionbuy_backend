package com.ateam.lionbuy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ateam.lionbuy.dto.LinkDTO;
import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.ProductMallDTO;
import com.ateam.lionbuy.service.CategoryService;
import com.ateam.lionbuy.service.CrawlingService;
import com.ateam.lionbuy.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/search")
@RequiredArgsConstructor
public class CrawlingController {
    
    @Autowired
    private final CrawlingService crawlingService;

    @Autowired
    private final ProductService productService;

    @GetMapping(value = "/info")
    public ResponseEntity<LinkDTO> url_parsing(@RequestParam("url") String url) {
        String[] pd_list = crawlingService.getKeyword(url);
        LinkDTO linkDTO = LinkDTO.builder()
            .pdName(pd_list[0])
            .price(pd_list[1])
            .build();
        return ResponseEntity.ok().body(linkDTO);
    }

    @GetMapping(value = "/tag")
    public ResponseEntity<Map<String, Object>> start_crawling(@RequestParam("item") String pdName) {
        crawlingService.start_crawling(pdName);
        Map<String, Object> productDetail = productService.getProduct(pdName);
        return ResponseEntity.ok().body(productDetail);
    }
}
