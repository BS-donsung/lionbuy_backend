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
@Log4j2
public class CrawlingController {
    
    @Autowired
    private final CrawlingService crawlingService;

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final ProductService productService;

    @GetMapping(value = "/info")
    public ResponseEntity<LinkDTO> url_parsing(@RequestParam("url") String url) {
        String[] pd_list = crawlingService.getKeyword(url);
        LinkDTO linkDTO = LinkDTO.builder()
            .pd_name(pd_list[0])
            .price(pd_list[1])
            .build();
        return ResponseEntity.ok().body(linkDTO);
    }

    @GetMapping(value = "/tag")
    public ResponseEntity<Map<String, Object>> start_crawling(@RequestParam("item") String pd_name) {
        crawlingService.start_crawling(pd_name);
        Set<String> tags = categoryService.relation_categories(pd_name);
        ProductMallDTO productMallDTO = productService.getLowProduct_mall(pd_name);
        Map<String, Object> tags_map = new HashMap<String, Object>();
        tags_map.put("categories", tags);
        tags_map.put("productLowMall", productMallDTO);
        return ResponseEntity.ok().body(tags_map);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ProductDTO>> relation_product(@RequestParam("tags") String tags) {
        String[] tag_list = tags.split("\\s");
        return ResponseEntity.ok().body(productService.findByTags(tag_list));
    }
}
