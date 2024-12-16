package com.hongyan.toutiao.convert;

import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import com.hongyan.toutiao.model.db.User;
import com.hongyan.toutiao.model.dto.UserDetailDto;
import org.mapstruct.Mapper;

import static com.hongyan.toutiao.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface UserToUserDetailDto extends BeanConvertMapper<User, UserDetailDto> {

}