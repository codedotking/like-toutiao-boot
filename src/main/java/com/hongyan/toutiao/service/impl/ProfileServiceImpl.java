package com.hongyan.toutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyan.toutiao.mapper.ProfileMapper;
import com.hongyan.toutiao.model.db.Profile;
import com.hongyan.toutiao.service.IProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl extends ServiceImpl<ProfileMapper, Profile>
        implements IProfileService {

    /**
     * 根据用户ID查找对应的Profile对象
     *
     * @param userId 用户ID，用于查询Profile对象
     * @return 返回匹配的Profile对象，如果不存在则返回null
     */
    @Override
    public Profile findByUserId(Long userId) {
        // 使用lambda表达式进行查询，比较Profile对象的userId属性与方法参数userId的值
        // .one()方法表示只返回一个匹配项，如果存在多个匹配项，只返回其中一个
        return lambdaQuery().eq(Profile::getUserId, userId).one();
    }

}
