/**
 *
 *
 *
 *
 *
 */

package com.taurus.core.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taurus.core.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
