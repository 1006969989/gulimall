package com.yuan.gulimall.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.yuan.gulimall.product.service.BrandService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {

	@Autowired
	private BrandService brandService;
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedissonClient redissonClient;

	@Test
	public void contextLoads() throws FileNotFoundException {

		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		ops.set("HELLO","REDIS");
		System.out.println(ops.get("HELLO"));
	}

	@Test
	public void testRedisson() {
		System.out.println(redissonClient);
		RReadWriteLock json = redissonClient.getReadWriteLock("json");
		RLock rLock = json.readLock();
		//rLock.lock();
		//rLock.unlock();

	}

}
