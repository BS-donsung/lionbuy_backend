package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.ProductLowprice;

public interface ProductLowpriceRepository extends JpaRepository<ProductLowprice, Long>{
    
    @Query("select pl from ProductLowprice pl where pl.product.pno=:pno")
    Optional<List<ProductLowprice>> getProductLowprice(@Param("pno") Long pno);
}
