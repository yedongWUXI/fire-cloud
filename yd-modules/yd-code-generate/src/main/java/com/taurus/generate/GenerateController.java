package com.taurus.generate;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2020/7/21 12:49
 * @Modified by:
 */
@RestController
@RequestMapping("/generate")
public class GenerateController {


    @ApiOperation(value = "代码自动生成")
    @PostMapping("/generate")
    public void generate(@RequestBody GenerateInfo generateInfo) {

        new GenerateMain().generateMybatisPlus(generateInfo);
    }


    @ApiOperation(value = "代码部署")
    @GetMapping("/build")
    public void build() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://192.168.200.189:1888/job/taurus/build?token=AUTO_BUILD", Object.class);
    }

}
