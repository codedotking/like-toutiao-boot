package com.hongyan.toutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyan.toutiao.mapper.UserMapper;
import com.hongyan.toutiao.model.db.UserEntity;
import com.hongyan.toutiao.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
