package com.hongyan.toutiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongyan.toutiao.model.db.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据角色id获取所有权限。
     *
     * @param roleId 角色id
     * @return 权限
     */
    List<Permission> findByRoleId(@Param("roleId") Long roleId);

}
