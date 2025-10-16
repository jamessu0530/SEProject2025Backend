package com.ntou.auctionSite.service.serviceImpl;

import com.ntou.auctionSite.model.User;
import com.ntou.auctionSite.repository.LoginRepository;
import com.ntou.auctionSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private LoginRepository loginRepository;

    // 登入功能 
    @Override
    public User loginService(String id, String password) {
        return null;
    }

    //註冊功能
    @Override
    public void registerService(User user) {
        loginRepository.save(user);
    }
}
