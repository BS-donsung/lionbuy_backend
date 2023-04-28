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

    @Query("select w.product from WishItem w join w.userinfo u where u.useremail=:userEmail")
    Optional<List<Product>> wish_product(@Param("userEmail") String userEmail);

    @Modifying
    @Query("delete from WishItem w where w.product.pno=:pno and w.choicedate=:choiceDate and w.userinfo.usernum=:userNum")
    int delete_wish(@Param("pno") Long pno, @Param("choiceDate") LocalDate choiceDate, @Param("userNum") Long userNum);
}
