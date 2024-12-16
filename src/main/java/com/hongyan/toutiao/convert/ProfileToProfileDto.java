package com.hongyan.toutiao.convert;


import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import com.hongyan.toutiao.model.db.Profile;
import com.hongyan.toutiao.model.dto.ProfileDto;
import org.mapstruct.Mapper;

import static com.hongyan.toutiao.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * profile to profileDto
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface ProfileToProfileDto extends BeanConvertMapper<Profile, ProfileDto> {

}
