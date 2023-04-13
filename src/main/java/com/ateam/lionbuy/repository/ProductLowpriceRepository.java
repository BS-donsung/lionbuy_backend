package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Product_lowprice;

public interface ProductLowpriceRepository extends JpaRepository<Product_lowprice, Long>{
    
    @Query("select pl from Product_lowprice pl where pl.product.pd_name=:pd_name")
    Optional<List<Product_lowprice>> getProductLowprice(@Param("pd_name") String pd_name);
}
