package com.hongyan.toutiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongyan.toutiao.model.db.Article;
import com.hongyan.toutiao.model.dto.ArticlePageDto;
import com.hongyan.toutiao.model.res.Page;
import com.hongyan.toutiao.model.vo.article.ArticlePageVo;


/**
 * 文章服务
 */
public interface IArticleService extends IService<Article> {


    /**
     * 分页查询
     *
     * @param articlePage 请求
     * @return ret
     */
    Page<ArticlePageDto> queryPage(ArticlePageVo articlePage);


}
