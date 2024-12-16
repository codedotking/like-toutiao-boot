package com.hongyan.toutiao.convert;

import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import com.hongyan.toutiao.model.db.Profile;
import com.hongyan.toutiao.model.request.RegisterUserProfileRequest;
import org.mapstruct.Mapper;

import static com.hongyan.toutiao.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * RegisterUserProfileRequestToProfile
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface RegisterUserProfileRequestToProfile extends BeanConvertMapper<RegisterUserProfileRequest, Profile> {
}
