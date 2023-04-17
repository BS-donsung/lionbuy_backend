package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

    @Query("select p from Product p where p.pd_name=:pd_name")
    Optional<Product> getProduct(@Param("pd_name") String pd_name);

    @Query("select p from Product p where p.pd_name like concat('%', :pd_name, '%') ")
    List<Product> getRelatedList(@Param("pd_name") String pd_name);

    @Query("delete from Product p where p.pd_name=:pd_name")
    void deleteProduct(@Param("pd_name") String pd_name);
}
