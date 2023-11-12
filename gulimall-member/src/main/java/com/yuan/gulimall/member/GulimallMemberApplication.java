package com.yuan.gulimall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableFeignClients(basePackages = "com.yuan.gulimall.member.feign")
@MapperScan("com.yuan.gulimall.member.dao")
@EnableDiscoveryClient
@SpringBootApplication
@EnableRedisHttpSession
public class GulimallMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallMemberApplication.class, args);
	}

}
