package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.ProductLowprice;

public interface ProductLowpriceRepository extends JpaRepository<ProductLowprice, Long>{
    
    @Query("select pl from ProductLowprice pl " +
            "where pl.product.pno=:pno and pl.nowdate in (" +
            "select distinct pl2.nowdate from ProductLowprice pl2 " +
            "where pl2.product.pno=:pno " +
            "order by pl2.nowdate asc, pl2.pdlowprice asc " +
            ") " +
            "group by pl.pdlowprice " +
            "order by pl.nowdate asc, pl.pdlowprice asc " +
            "limit 5")
    Optional<List<ProductLowprice>> getProductLowprice(@Param("pno") Long pno);

    // select pl from ProductLowprice pl where pl.product.pno=:pno group by pl.nowdate order by pl.pdlowprice asc limit 5
    // select pl from ProductLowprice pl inner join (select pl2.nowdate, min(pl2.pdlowprice) as min_price from ProductLowprice pl2 where pl2.product.pno=:pno group by pl2.nowdate) pmin on pl.nowdate=pmin.nowdate and pl.pdlowprice=pmin.min_price where pl.product.pno=:pno order by pl.nowdate asc, pl.pdlowprice asc
}
