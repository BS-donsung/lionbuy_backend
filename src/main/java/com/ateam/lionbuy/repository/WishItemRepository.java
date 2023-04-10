package com.ateam.lionbuy.repository;

import com.ateam.lionbuy.entity.Buy_item;
import com.ateam.lionbuy.entity.User_info;
import com.ateam.lionbuy.entity.Wish_item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishItemRepository extends JpaRepository<Wish_item, Long> {

}
