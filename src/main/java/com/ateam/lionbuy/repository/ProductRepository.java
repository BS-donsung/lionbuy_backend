package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{


    Optional<Product> findByPdname(String pdName);

    @Query("select p from Product p where p.pdname like concat('%', :pdName, '%') ")
    Optional<List<Product>> getRelatedList(@Param("pdName") String pdName);

    @Query("delete from Product p where p.pdname=:pdName")
    void deleteProduct(@Param("pdName") String pdName);


}
