package com.hongyan.toutiao.repository;

import com.hongyan.toutiao.model.db.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<ArticleEntity, Long> {
}