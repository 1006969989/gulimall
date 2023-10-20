package com.yuan.gulimall.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.yuan.gulimall.product.service.BrandService;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {

	@Autowired
	private BrandService brandService;

	@Test
	public void contextLoads() throws FileNotFoundException {


	}

}
