package com.ateam.lionbuy.service;

import java.util.List;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.entity.Product;

public interface ProductService {
    
    List<ProductDTO> findByTags(String[] tags);

    default ProductDTO product_build_dto(Product product){
        ProductDTO productDTO = ProductDTO.builder()
            .pd_name(product.getPd_name())
            .image_url(product.getImage_url())
            .pd_lowprice(product.getPd_lowprice())
            .build();
        return productDTO;
    }
}
