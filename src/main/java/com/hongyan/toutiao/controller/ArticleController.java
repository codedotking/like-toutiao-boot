package com.hongyan.toutiao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongyan.toutiao.model.db.ArticleEntity;
import com.hongyan.toutiao.model.req.PagingModel;
import com.hongyan.toutiao.model.res.ResultModel;
import com.hongyan.toutiao.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/article")
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
     * @param pagingModel 分页模型，包含分页查询所需的参数
     * @return 返回包含分页文章数据的ResultModel对象
     */
    @GetMapping("/paging")
    public ResultModel page(PagingModel pagingModel) {
        try {
            // 调用文章服务的分页方法，传入经过通用分页模型转换后的分页参数
            Page<ArticleEntity> articlePageData = this.articleService.page(PagingModel.genPagingModel(pagingModel));
            // 日志记录分页数据信息
            log.info("Fetched {} articles for page {}", articlePageData);
            // 返回成功的结果模型，包含分页文章数据
            return ResultModel.success(articlePageData);
        } catch (Exception e) {
            log.error("Error fetching article page data", e);
            return ResultModel.fail("Failed to fetch article page data");
        }
    }
}
