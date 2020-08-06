/**
 *
 *
 *
 *
 *
 */

package com.taurus.core.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taurus.common.utils.PageUtils;
import com.taurus.core.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
