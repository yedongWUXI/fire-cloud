package com.taurus.mapper;

import com.taurus.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yedong
 * @since 2020-07-21
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
