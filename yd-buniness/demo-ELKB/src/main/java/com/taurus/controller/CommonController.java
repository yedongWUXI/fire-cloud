package com.taurus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2020/6/12 14:48
 * @Modified by:
 */
@Api(value = "/swagger-test",tags = "通用测试")
@RestController
@RequestMapping("/swagger-test")
public class CommonController {


    @ApiOperation(value = "controller类测试")
    @GetMapping("/commonTest")
    public String test(String s){
        return "success";
    }
}
