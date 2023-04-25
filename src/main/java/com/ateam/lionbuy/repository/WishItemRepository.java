package com.ateam.lionbuy.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Product;
import com.ateam.lionbuy.entity.WishItem;

public interface WishItemRepository extends JpaRepository<WishItem, Long> {

    @Query("select w.product from WishItem w join w.userInfo u where u.userEmail=:userEmail")
    Optional<List<Product>> wish_product(@Param("userEmail") String userEmail);

    @Modifying
    @Query("delete from WishItem w where w.product.pdName=:pdName and w.choiceDate=:choiceDate")
    int delete_wish(@Param("pdName") String pdName, @Param("choiceDate") LocalDate choiceDate);
}
