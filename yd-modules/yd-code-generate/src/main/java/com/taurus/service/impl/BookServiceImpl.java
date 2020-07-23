package com.taurus.service.impl;

import com.taurus.entity.BookEntity;
import com.taurus.mapper.BookMapper;
import com.taurus.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yedong
 * @since 2020-07-21
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookEntity> implements BookService {

}
