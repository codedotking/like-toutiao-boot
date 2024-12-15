package com.hongyan.toutiao.controller;


import com.hongyan.toutiao.model.db.UserEntity;
import com.hongyan.toutiao.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

   private final IUserService userService;


   public UserController(IUserService userService) {
       this.userService = userService;
   }

    /**
     * 用户登录接口
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    /**
     * 查找所有用户数据
     * @return
     */
    @GetMapping("/list")//查找所有数据
    public List<UserEntity> findAll() {
        return this.userService.list();
    }


}
