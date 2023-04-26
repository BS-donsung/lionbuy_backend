package com.ateam.lionbuy.service;

import java.util.List;
import java.util.Map;

import com.ateam.lionbuy.dto.ProductDTO;
import com.ateam.lionbuy.dto.ProductLowpriceDTO;
import com.ateam.lionbuy.dto.ProductMallDTO;
import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.ProductLowprice;
import com.ateam.lionbuy.entity.ProductMall;

public interface ProductService {
    
    List<ProductDTO> findByTags(String[] tags);

    Map<String, Object> getProduct(String pd_name);

    ProductMallDTO getLowProduct_mall(String pd_name);

    default ProductDTO product_build_dto(Product product){
        ProductDTO productDTO = ProductDTO.builder()
            .pno(product.getPno())
            .pdName(product.getPdname())
            .imageUrl(product.getImageurl())
            .pdLowprice(product.getPdlowprice())
            .build();
        return productDTO;
    }

    default ProductLowpriceDTO lowprice_build_dto(ProductLowprice productLowprice) {
        ProductLowpriceDTO productLowpriceDTO = ProductLowpriceDTO.builder()
            .pdName(productLowprice.getProduct().getPdname())
            .pdLowprice(Long.valueOf(productLowprice.getPdlowprice()))
            .nowDate(productLowprice.getNowdate())
            .build();
        return productLowpriceDTO;
    }

    default ProductMallDTO mall_build_dto(ProductMall productMall) {
        ProductMallDTO productMallDTO = ProductMallDTO.builder()
            .pdName(productMall.getProduct().getPdname())
            .mallName(productMall.getMallname())
            .price(productMall.getPrice())
            .build();
        return productMallDTO;
    }
}
