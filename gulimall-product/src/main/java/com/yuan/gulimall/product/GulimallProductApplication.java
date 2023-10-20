package com.yuan.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 配置mybatis-plus
 * 1.导入mybatis-plus依赖
 *  		<dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus-boot-starter</artifactId>
 *             <version>3.2.0</version>
 *         </dependency>
 * 2.设置数据库源
 *    1)导入MySQL依赖、导入mysql驱动
 *         <dependency>
 *             <groupId>mysql</groupId>
 *             <artifactId>mysql-connector-java</artifactId>
 *             <version>8.0.17</version>
 *         </dependency>
 *    2)配置数据源(yml配置datasource)
 *    3)配置mybatis
 *       1.使用mapperScan注解
 *       2.配置mapper.xml路径
 *       3.配置主键自增
 *
 */
@EnableDiscoveryClient
@MapperScan("com.yuan.gulimall.product.dao")
@SpringBootApplication
public class GulimallProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallProductApplication.class, args);
	}

}
