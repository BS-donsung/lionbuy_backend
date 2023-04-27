package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Category;

import jakarta.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    @Query("select distinct c from Category c where c.product.pno=:pno")
    List<Category> get_categories(@Param("pno") Long pno);

    @Query("select c from Category c where c.product.pno=:pno")
    Optional<Category> getCategories(@Param("pno") Long pno);

    @Transactional
    @Modifying
    @Query("update Category c set c.categories=:categories where c.product.pno=:pno")
    int updateCategoryData(@Param("categories") String categories, @Param("pno") Long pno);
}
