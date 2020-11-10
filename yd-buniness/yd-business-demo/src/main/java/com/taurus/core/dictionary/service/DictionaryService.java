package com.taurus.core.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taurus.common.utils.PageUtils;
import com.taurus.core.dictionary.entity.DictionaryEntity;

import java.util.Map;

/**
 * 
 *
 * @author yeDong
 * @email yeDong@gmail.com
 * @date 2020-09-08 18:19:10
 */
public interface DictionaryService extends IService<DictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

