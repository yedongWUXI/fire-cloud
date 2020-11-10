package com.taurus.core.dictionary.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.taurus.core.dictionary.entity.DictionaryEntity;
import com.taurus.core.dictionary.service.DictionaryService;
import com.taurus.common.utils.PageUtils;


/**
 * 
 *
 * @author yeDong
 * @email yeDong@gmail.com
 * @date 2020-09-08 18:19:10
 */
@Api(tags = "Dictionary")
@RestController
@RequestMapping("dictionary/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions("dictionary:dictionary:list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        PageUtils page = dictionaryService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @ApiOperation(value = "信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("dictionary:dictionary:info")
    public DictionaryEntity info(@PathVariable("id") String id) {
            DictionaryEntity dictionary = dictionaryService.getById(id);

        return dictionary;
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @RequiresPermissions("dictionary:dictionary:save")
    public void save(@RequestBody DictionaryEntity dictionary) {
            dictionaryService.save(dictionary);

    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiresPermissions("dictionary:dictionary:update")
    public void update(@RequestBody DictionaryEntity dictionary) {
            dictionaryService.updateById(dictionary);

    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("dictionary:dictionary:delete")
    public void delete(@RequestBody String[]ids) {
            dictionaryService.removeByIds(Arrays.asList(ids));

    }

}
