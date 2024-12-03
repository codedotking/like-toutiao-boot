package com.hongyan.toutiao.repository;

import com.hongyan.toutiao.model.db.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//表示这个是一个操作数据库的Repository类
public interface UserRepo extends JpaRepository<UserEntity, Long> {
}