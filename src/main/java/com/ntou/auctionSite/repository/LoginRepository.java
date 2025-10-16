package com.ntou.auctionSite.repository;

import com.ntou.auctionSite.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepository extends MongoRepository<User, String> {
    User findByUserName(String userName);
}
