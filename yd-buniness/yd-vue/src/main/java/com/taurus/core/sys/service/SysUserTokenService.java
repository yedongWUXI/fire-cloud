/**
 *
 *
 *
 *
 *
 */

package com.taurus.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taurus.core.sys.entity.SysUserTokenEntity;

import java.util.Map;

/**
 * 用户Token
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    Map<String, Object> createToken(long userId);

    /**
     * 退出，修改token值
     *
     * @param userId 用户ID
     */
    void logout(long userId);

}
