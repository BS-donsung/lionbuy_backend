package com.ateam.lionbuy.repository;

import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyItemRepository extends JpaRepository<Buy_item, Long> {

    @Query("select b from Buy_item b where month(b.buy_date)=:month and year(b.buy_date)=:year and b.user_info.user_email=:user_email ")
    List<Buy_item> get_buyitems(@Param("month") Long month, @Param("year") Long year, @Param("user_email") String user_email);
}
