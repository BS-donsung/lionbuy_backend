package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("select p.pdname from Product p")
    List<String> getPdName();

    @Transactional
    @Modifying
    @Query("update Product p set p.imageurl=:imageurl, p.pdlowprice=:pdlowprice where p.pno=:pno")
    int updateProductData(@Param("pno") Long pno, @Param("imageurl") String imageurl, @Param("pdlowprice") String pdlowprice);

    @Query("select p from Product p where p.pdname=:pdName")
    Optional<Product> getProduct(@Param("pdName") String pdName);

    @Query("select p from Product p where p.pdname like concat('%', :pdName, '%') ")
    Optional<List<Product>> getRelatedList(@Param("pdName") String pdName);

    @Query("delete from Product p where p.pdname=:pdName")
    void deleteProduct(@Param("pdName") String pdName);

    @Query("select p from Product p where p.pdname=:pdName")
    Optional<Product> getPno(@Param("pdName") String pdName);
}
