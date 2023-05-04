package com.ateam.lionbuy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            Object[] pd_list = crawlingService.getKeyword(url);
            LinkDTO linkDTO = LinkDTO.builder()
            .pdName(String.valueOf(pd_list[0]))
            .price(String.valueOf(pd_list[1]))
            .imageList(pd_list[2])
            .build();
<<<<<<< HEAD

        return ResponseEntity.ok().body(linkDTO);
=======
            return ResponseEntity.ok().body(linkDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url Not Found");
        }
>>>>>>> fddeeae63040e32490e597989c5ddecdf005084d
    }

    @GetMapping(value = "/tag")
    public ResponseEntity<Map<String, Object>> start_crawling(@RequestParam("item") String pdName) {
        try {
            crawlingService.start_crawling(pdName);
            Map<String, Object> productDetail = productService.getProduct(pdName);
            return ResponseEntity.ok().body(productDetail);
        } catch (Exception e) {
            return ResponseEntity.ok().body(null);
        }
    }
}
