/**
 */

package com.taurus.core.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taurus.core.sys.dao.SysUserTokenDao;
import com.taurus.core.sys.entity.SysUserTokenEntity;
import com.taurus.core.sys.oauth2.TokenGenerator;
import com.taurus.core.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;


    @Override
    public Map<String, Object> createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = this.getById(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        Map<String, Object> map = new HashMap<>();

        map.put("token", token);
        map.put("expire", EXPIRE);

        return map;
    }

    @Override
    public void logout(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}
