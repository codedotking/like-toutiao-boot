package com.hongyan.toutiao.convert;

import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import com.hongyan.toutiao.model.db.Role;
import com.hongyan.toutiao.model.dto.RolePageDto;
import org.mapstruct.Mapper;

import static com.hongyan.toutiao.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;


/**
 * role to roleDto
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface RoleToRolePageDto extends BeanConvertMapper<Role, RolePageDto> {

}
