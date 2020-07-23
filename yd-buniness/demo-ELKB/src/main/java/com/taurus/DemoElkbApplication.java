package com.taurus;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableSwaggerBootstrapUI
@SpringBootApplication
@RestController
@EnableGlobalDispose
public class DemoElkbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoElkbApplication.class, args);
	}


	private Logger log = LoggerFactory.getLogger(this.getClass());


	@GetMapping("test")
	public String test(String str){
		return "yd-business";
	}

}
