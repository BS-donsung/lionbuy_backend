package com.ateam.lionbuy.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ateam.lionbuy.dto.LinkDTO;
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
        if(url != null) {
            Object[] pd_list = crawlingService.getKeyword(url);
            LinkDTO linkDTO = LinkDTO.builder()
                .pdName(String.valueOf(pd_list[0]))
                .price(String.valueOf(pd_list[1]))
                .imageList(pd_list[2])
                .build();
            return ResponseEntity.ok().body(linkDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/tag")
    public ResponseEntity<Map<String, Object>> start_crawling(@RequestParam("item") String pdName) {
        // try {
        //     crawlingService.start_crawling(pdName);
        //     Map<String, Object> productDetail = productService.getProduct(pdName);
        //     return ResponseEntity.ok().body(productDetail);
        // } catch (Exception e) {
        //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyMap());
        // }
        crawlingService.start_crawling(pdName);
        Map<String, Object> productDetail = productService.getProduct(pdName);
        return ResponseEntity.ok().body(productDetail);
    }
}
