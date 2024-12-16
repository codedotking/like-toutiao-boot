package com.hongyan.toutiao.convert;

import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import com.hongyan.toutiao.model.db.User;
import com.hongyan.toutiao.model.request.RegisterUserRequest;
import org.mapstruct.Mapper;

import static com.hongyan.toutiao.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;


@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface RegisterUserRequestToUser
        extends BeanConvertMapper<RegisterUserRequest, User> {

}
