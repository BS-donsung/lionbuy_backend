package com.ateam.lionbuy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.ProductMall;

public interface ProductMallRepository extends JpaRepository<ProductMall, Long>{
    
    @Query("select pm from ProductMall pm where pm.price = (select min(pm2.price) from ProductMall pm2 where pm2.product.pno=:pno)")
    Optional<ProductMall> getLowMall(@Param("pno") Long pno);
}

