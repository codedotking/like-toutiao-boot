package com.hongyan.toutiao.controller;


import com.hongyan.toutiao.repository.UserRepo;
import com.hongyan.toutiao.model.db.UserEntity;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserRepo userRepo;


    @GetMapping("/list")//查找所有数据
    public List<UserEntity> findAll() {
        return this.userRepo.findAll();
    }


}
