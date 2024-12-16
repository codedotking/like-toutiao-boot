package com.hongyan.toutiao.convert;

import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import com.hongyan.toutiao.model.db.Profile;
import com.hongyan.toutiao.model.dto.ProfileDto;
import org.mapstruct.Mapper;

import static com.hongyan.toutiao.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * ProfileDtoToProfile
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface ProfileDtoToProfile extends BeanConvertMapper<ProfileDto, Profile> {
}
