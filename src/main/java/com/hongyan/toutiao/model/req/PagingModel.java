package com.hongyan.toutiao.model.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongyan.toutiao.model.db.ArticleEntity;
import lombok.Data;

@Data
public class PagingModel {

    // 分页模型
    private Integer pageNum;
    private Integer pageSize;
    private String orderBy;
    private String orderType;
    private String keyword;



    public  static <T> Page<T>  dd (PagingModel pagingModel){
        Page<T> page = new Page<>(pagingModel.getPageNum(), pagingModel.getPageSize());
        return page;
    }
}
