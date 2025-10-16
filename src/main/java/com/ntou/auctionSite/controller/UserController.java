package com.ntou.auctionSite.controller;

import com.ntou.auctionSite.model.User;
import com.ntou.auctionSite.repository.LoginRepository;
import com.ntou.auctionSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //測試伺服器是否運行
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is running");
    }

    //註冊功能
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerService(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }
}
