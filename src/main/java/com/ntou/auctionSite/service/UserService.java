package com.ntou.auctionSite.service;

import com.ntou.auctionSite.model.User;

public interface UserService {
    // 登入功能
    User loginService(String id, String password);

    //註冊功能
    void registerService(User user);
}
