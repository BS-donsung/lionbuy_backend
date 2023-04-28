package com.ateam.lionbuy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{

    Optional<UserInfo> findByUseremail(String userEmail);

}
