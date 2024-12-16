package com.hongyan.toutiao.convert;

import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import com.hongyan.toutiao.model.db.Permission;
import com.hongyan.toutiao.model.request.UpdatePermissionRequest;
import org.mapstruct.Mapper;

import static com.hongyan.toutiao.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;


/**
 * UpdatePermissionRequestToPermission
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface UpdatePermissionRequestToPermission
    extends BeanConvertMapper<UpdatePermissionRequest, Permission> {

}
