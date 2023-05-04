package com.ateam.lionbuy.controller

import com.ateam.lionbuy.dto.LinkDTO
import com.ateam.lionbuy.service.CrawlingService
import com.ateam.lionbuy.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search/product")
class RestCrawlingController
@Autowired constructor(
    val crawlingService: CrawlingService,
    val productService : ProductService
){

}