package com.ateam.lionbuy.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.Buy_item;

public interface BuyItemRepository extends JpaRepository<Buy_item, Long> {

    @Query("select b from Buy_item b where month(b.buy_date)=:month and year(b.buy_date)=:year and b.user_info.user_email=:user_email ")
    List<Buy_item> get_buyitems(@Param("month") Long month, @Param("year") Long year, @Param("user_email") String user_email);

    @Modifying
    @Query("delete from Buy_item b where b.product.pd_name=:pd_name and b.buy_date=:buy_date")
    int delete_buy(@Param("pd_name") String pd_name, @Param("buy_date") LocalDateTime buy_date);
}
