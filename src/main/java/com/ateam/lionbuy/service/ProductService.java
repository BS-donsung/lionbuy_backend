package com.ateam.lionbuy.service;

import java.util.List;
import java.util.Map;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.ProductLowpriceDTO;
import com.ateam.lionbuy.dto.ProductMallDTO;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.Product_lowprice;
import com.ateam.lionbuy.entity.Product_mall;

public interface ProductService {
    
    List<ProductDTO> findByTags(String[] tags);

    Map<String, Object> getUserProduct(String pd_name);

    ProductMallDTO getLowProduct_mall(String pd_name);

    default ProductDTO product_build_dto(Product product){
        ProductDTO productDTO = ProductDTO.builder()
            .pd_name(product.getPd_name())
            .image_url(product.getImage_url())
            .pd_lowprice(product.getPd_lowprice())
            .build();
        return productDTO;
    }

    default ProductLowpriceDTO lowprice_build_dto(Product_lowprice product_lowprice) {
        ProductLowpriceDTO productLowpriceDTO = ProductLowpriceDTO.builder()
            .plno(product_lowprice.getPlno())
            .pd_name(product_lowprice.getProduct().getPd_name())
            .pd_lowprice(Long.valueOf(product_lowprice.getPd_lowprice()))
            .now_date(product_lowprice.getNow_date())
            .build();
        return productLowpriceDTO;
    }

    default ProductMallDTO mall_build_dto(Product_mall product_mall) {
        ProductMallDTO productMallDTO = ProductMallDTO.builder()
            .pmno(product_mall.getPmno())
            .pd_name(product_mall.getProduct().getPd_name())
            .mall_name(product_mall.getMall_name())
            .price(product_mall.getPrice())
            .now_date(product_mall.getNow_date())
            .build();
        return productMallDTO;
    }
}
