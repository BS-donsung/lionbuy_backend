package com.ateam.lionbuy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ateam.lionbuy.dto.LinkDTO;
import com.ateam.lionbuy.service.CrawlingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/search")
@RequiredArgsConstructor
public class CrawlingController {
    
    @Autowired
    private final CrawlingService crawlingService;

    @GetMapping(value = "/info")
    public ResponseEntity<LinkDTO> url_parsing(@RequestParam("url") String url) {
        String[] pd_list = crawlingService.getKeyword(url);
        LinkDTO linkDTO = LinkDTO.builder()
            .pd_name(pd_list[0])
            .price(pd_list[1])
            .build();
        return ResponseEntity.ok().body(linkDTO);
    }

    @PostMapping(value = "/tag")
    public ResponseEntity<String> start_crawling(@RequestParam("item") String pd_name) {
        String crawling_result = crawlingService.start_crawling(pd_name);
        return ResponseEntity.ok().body(crawling_result);
    }
}
