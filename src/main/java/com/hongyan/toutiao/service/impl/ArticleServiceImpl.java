package com.hongyan.toutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyan.toutiao.mapper.ArticleMapper;
import com.hongyan.toutiao.model.db.ArticleEntity;
import com.hongyan.toutiao.service.IArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements IArticleService {

}
