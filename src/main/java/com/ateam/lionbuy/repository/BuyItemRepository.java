package com.ateam.lionbuy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.BuyItem;

public interface BuyItemRepository extends JpaRepository<BuyItem, Long> {

    @Query("select b from BuyItem b where month(b.buydate)=:month and year(b.buydate)=:year and b.userinfo.useremail=:userEmail ")
    List<BuyItem> get_buyitems(@Param("month") Long month, @Param("year") Long year, @Param("userEmail") String userEmail);

    @Modifying
    @Query("delete from BuyItem b where b.product.pno=:pno and b.buydate=:buyDate")
    int delete_buy(@Param("pno") Long pno, @Param("buyDate") LocalDate buydate);
}
