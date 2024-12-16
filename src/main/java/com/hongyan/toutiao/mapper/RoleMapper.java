package com.hongyan.toutiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongyan.toutiao.model.db.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id获取角色列表.
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> findRolesByUserId(@Param("userId") Long userId);

}
