package com.hongyan.toutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyan.toutiao.mapper.ArticleMapper;
import com.hongyan.toutiao.model.db.Article;
import com.hongyan.toutiao.model.dto.ArticlePageDto;
import com.hongyan.toutiao.model.res.Page;
import com.hongyan.toutiao.model.vo.article.ArticlePageVo;
import com.hongyan.toutiao.service.IArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public Page<ArticlePageDto> queryPage(ArticlePageVo request) {
        return null;
    }
}
