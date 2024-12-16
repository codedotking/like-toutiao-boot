package com.hongyan.toutiao.controller;

import com.hongyan.toutiao.model.dto.ArticlePageDto;
import com.hongyan.toutiao.model.res.Page;
import com.hongyan.toutiao.model.res.R;
import com.hongyan.toutiao.model.vo.article.ArticlePageVo;
import com.hongyan.toutiao.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    // 注入文章服务接口
    private final IArticleService articleService;

    // 构造函数，注入文章服务接口
    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 获取文章列表的分页信息
     *
     * @param articlePage 分页模型，包含分页查询所需的参数
     * @return 返回包含分页文章数据的ResultModel对象
     */
    @GetMapping("/paging")
    public R<Page<ArticlePageDto>> page(ArticlePageVo articlePage) {
        Page<ArticlePageDto> ret = articleService.queryPage(articlePage);
        return R.ok(ret);
    }
}
