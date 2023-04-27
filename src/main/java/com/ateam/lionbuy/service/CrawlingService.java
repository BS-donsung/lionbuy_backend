package com.ateam.lionbuy.service;

import java.io.IOException;
import java.util.Map;

import com.ateam.lionbuy.entity.Category;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.ProductLowprice;
import com.ateam.lionbuy.entity.ProductMall;
import com.ateam.lionbuy.util.FileMake;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface CrawlingService {

    FileMake fm = new FileMake();

    String[] getKeyword(String url);

    void periodic_crawling();

    String start_crawling(String keyword);


    default String[] data_preprocessing(String response) {
        String split_response = (response.split("application/json")[1]).split(">")[1].split("</scrip")[0];
        String list_split_response = (split_response.split("\"list\":\\[\\{")[1]).split("\\],\"total\":")[0];
        return list_split_response.split(",\"type\":\"product\"\\},\\{\"item\":");
    }

    default Map<String, Object> StringToMap(String result) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> returnMap = mapper.readValue(result, Map.class);
        return returnMap;
    }

    default Product product_build(Map<String, Object> returnMap) {
        Product product = Product.builder()
            .pdname(String.valueOf(returnMap.get("productTitle")))
            .imageurl(String.valueOf(returnMap.get("imageUrl")))
            .pdlowprice(String.valueOf(returnMap.get("lowPrice")))
            .build();
        return product;
    }

    default Category category_build(Product product, String categories) {
        Category category = Category.builder()
            .product(product)
            .categories(categories)
            .build();
        return category;
    }

    default ProductLowprice lowprice_build(Product product, Map<String, Object> returnmap) {
        ProductLowprice lowprice = ProductLowprice.builder()
            .product(product)
            .pdlowprice(String.valueOf(returnmap.get("lowPrice")))
            .build();
        return lowprice;
    }

    default ProductMall mall_build_entity(Product product, Map<String, Object> returnmap, String mallName, Long price) {
        ProductMall mall = ProductMall.builder()
            .product(product)
            .mallname(mallName)
            .price(price)
            .build();
        return mall;
    }


}
