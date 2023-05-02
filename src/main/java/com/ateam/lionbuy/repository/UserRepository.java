package com.ateam.lionbuy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ateam.lionbuy.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{
    
    @Query("select u from UserInfo u where u.useremail=:userEmail")
    Optional<UserInfo> getInfo(@Param("userEmail") String userEmail);

    // Optional<UserInfo> findByUseremail(String userEmail);
}
