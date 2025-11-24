package com.ntou.auctionSite.repository;

import com.ntou.auctionSite.model.cart.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    /**
     * 根據使用者 ID 查詢購物車
     * 一個使用者只有一個購物車
     */
    Optional<Cart> findByUserId(String userId);

    /**
     * 刪除使用者的購物車
     */
    void deleteByUserId(String userId);
}

