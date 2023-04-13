package com.ateam.lionbuy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Product_mall;

public interface ProductMallRepository extends JpaRepository<Product_mall, Long>{
    
    @Query("select pm from Product_mall pm where pm.price = (select min(pm2.price) from Product_mall pm2 where pm2.product.pd_name=:pd_name)")
    Optional<Product_mall> getLowMall(@Param("pd_name") String pd_name);
}
