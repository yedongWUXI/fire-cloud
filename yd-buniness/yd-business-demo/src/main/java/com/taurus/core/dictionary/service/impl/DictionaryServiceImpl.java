package com.taurus.core.dictionary.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taurus.common.utils.PageUtils;
import com.taurus.common.utils.Query;

import com.taurus.core.dictionary.dao.DictionaryDao;
import com.taurus.core.dictionary.entity.DictionaryEntity;
import com.taurus.core.dictionary.service.DictionaryService;


@Service("dictionaryService")
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, DictionaryEntity> implements DictionaryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DictionaryEntity> page = this.page(
                new Query<DictionaryEntity>().getPage(params),
                new QueryWrapper<DictionaryEntity>()
        );

        return new PageUtils(page);
    }

}