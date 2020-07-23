package com.taurus.schedule;

import com.taurus.tools.WebToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2020/6/11 15:42
 * @Modified by:
 */
@Component
@Configuration
@EnableScheduling
public class LogSchedule {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Scheduled(fixedDelay=1000)
    public void logTest(){


        try {
            log.info("当前服务ip:{}",WebToolUtils.getLocalIP());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        log.info("当前时间：{}", LocalDateTime.now());

    }
}
