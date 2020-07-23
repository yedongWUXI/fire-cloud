package com.taurus.controller;



import com.taurus.service.BookService;
import com.taurus.entity.BookEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yedong
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/book-entity")
public class BookController {

    @Autowired
    public BookService bookEntityService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Boolean save(@RequestBody BookEntity bookEntity){
        return bookEntityService.save(bookEntity);
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") Long id){
        return bookEntityService.removeById(id);
    }

    @ApiOperation(value = "条件查询")
    @PostMapping("/get")
    public List<BookEntity> list(@RequestBody BookEntity bookEntity){
        List<BookEntity> bookEntityList = bookEntityService.list(new QueryWrapper<>(bookEntity));
        return bookEntityList;
    }

    @ApiOperation(value = "列表（分页）")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public IPage list(@PathVariable("pageNum")Long pageNum, @PathVariable("pageSize")Long pageSize){
        IPage<BookEntity> page = bookEntityService.page(
                new Page<>(pageNum, pageSize),
                null);
        return page;
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get/{id}")
    public BookEntity get(@PathVariable("id") Long id){
        BookEntity bookEntity = bookEntityService.getById(id);
        return bookEntity;
    }

    @ApiOperation(value = "根据id修改")
    @PostMapping("/update/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody BookEntity bookEntity){
        bookEntity.setId(id);
        return bookEntityService.updateById(bookEntity);
    }


}
