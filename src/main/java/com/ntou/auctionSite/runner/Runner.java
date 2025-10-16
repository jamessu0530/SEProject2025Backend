package com.ntou.auctionSite.runner;

import com.ntou.auctionSite.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {
    private UserService userService;

    public Runner(UserService userService){
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
