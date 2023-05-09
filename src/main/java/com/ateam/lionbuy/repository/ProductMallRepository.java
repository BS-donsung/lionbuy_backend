package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.ProductMall;

import jakarta.transaction.Transactional;

public interface ProductMallRepository extends JpaRepository<ProductMall, Long>{
    
    @Query("select pm from ProductMall pm where pm.price = (select min(pm2.price) from ProductMall pm2 where pm2.product.pno=:pno)")
    Optional<ProductMall> getLowMall(@Param("pno") Long pno);

    @Query("select pm from ProductMall pm where pm.product.pno=:pno")
    Optional<List<ProductMall>> getMalldata(@Param("pno") Long pno);

    @Query("select pm from ProductMall pm where pm.product.pno=:pno")
    Optional<ProductMall> getOneMalldata(@Param("pno") Long pno);

    @Transactional
    @Modifying
    @Query("update ProductMall pm set pm.mallname=:mallname, pm.price=:price where pm.product.pno=:pno")
    int updateMallData(@Param("mallname") String mallname, @Param("price") Long price, @Param("pno") Long pno);
}

