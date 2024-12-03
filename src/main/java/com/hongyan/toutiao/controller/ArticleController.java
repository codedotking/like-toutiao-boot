package com.hongyan.toutiao.controller;


import com.hongyan.toutiao.model.db.ArticleEntity;
import com.hongyan.toutiao.model.db.UserEntity;
import com.hongyan.toutiao.repository.ArticleRepo;
import com.hongyan.toutiao.repository.UserRepo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleRepo articleRepo;

    @GetMapping("/list")//查找所有数据
    public List<ArticleEntity> findAll() {
        return this.articleRepo.findAll();
    }
}
