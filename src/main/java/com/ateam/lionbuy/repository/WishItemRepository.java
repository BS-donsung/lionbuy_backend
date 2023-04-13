package com.ateam.lionbuy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.Wish_item;

public interface WishItemRepository extends JpaRepository<Wish_item, Long> {

    @Query("select w.product from Wish_item w join w.user_info u where u.user_email=:user_email")
    Optional<List<Product>> wish_product(@Param("user_email") String user_email);
}
