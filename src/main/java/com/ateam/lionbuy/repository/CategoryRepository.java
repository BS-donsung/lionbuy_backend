package com.ateam.lionbuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    @Query("select distinct c from Category c where c.product.pd_name=:pd_name")
    List<Category> get_categories(@Param("pd_name") String pd_name);
}
