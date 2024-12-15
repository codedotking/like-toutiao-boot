package com.hongyan.toutiao.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongyan.toutiao.model.db.ArticleEntity;
import com.hongyan.toutiao.model.req.PagingModel;
import com.hongyan.toutiao.service.impl.ArticleServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleServiceImpl articleService;

    @GetMapping("/list")//查找所有数据
    public List<ArticleEntity> findAll() {
        return this.articleService.list();
    }


    @GetMapping("/paging")
    public Page<ArticleEntity> page(PagingModel pagingModel) {
        return this.articleService.page(PagingModel.dd(pagingModel));
    }

}
