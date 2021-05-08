package com.taurus;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.taurus.flow.config.ApplicationConfiguration;
import com.taurus.flow.servlet.AppDispatcherServletConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @title: : FlowManagerApplication
 * @projectName : flowable
 * @description: 启动类
 */
@Import({
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class
})
@EnableSwagger2
@EnableSwaggerBootstrapUI
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class FlowManagerApplication {
    private static final Logger logger = LoggerFactory.getLogger(FlowManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FlowManagerApplication.class, args);
        logger.info("###########################流程后台程序启动成功##################################");
    }
}
