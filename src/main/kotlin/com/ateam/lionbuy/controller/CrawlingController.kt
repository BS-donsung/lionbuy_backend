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

    @GetMapping("/test")
    fun test() : String = "/search/product"

    @GetMapping
    fun `search by url`(@RequestParam("url") url : String) : ResponseEntity<Map<String, Any>> {
        val pd_list: Array<String> = crawlingService.getKeyword(url)
        val pdname = pd_list[0]

        crawlingService.start_crawling(pdname)
        val productDetail: Map<String, Any> = productService.getProduct(pdname)
        return ResponseEntity.ok().body(productDetail)
    }
}