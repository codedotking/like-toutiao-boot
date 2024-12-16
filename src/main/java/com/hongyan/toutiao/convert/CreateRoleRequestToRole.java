package com.hongyan.toutiao.convert;


import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import com.hongyan.toutiao.model.db.Role;
import com.hongyan.toutiao.model.request.CreateRoleRequest;
import org.mapstruct.Mapper;

import static com.hongyan.toutiao.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * CreateRoleRequestToRole
 *
 * @author dhb
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface CreateRoleRequestToRole extends BeanConvertMapper<CreateRoleRequest, Role> {
}
