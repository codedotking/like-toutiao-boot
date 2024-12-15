package com.hongyan.toutiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongyan.toutiao.model.db.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

}
