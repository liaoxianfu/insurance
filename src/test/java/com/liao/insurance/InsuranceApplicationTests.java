package com.liao.insurance;

import com.liao.insurance.entity.User;
import com.liao.insurance.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InsuranceApplicationTests {

    //    @Autowired
//    private UserMapper mapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void contextLoads() {
//        User user = new User();
//        user.setPassword("1234");
//        user.setUsername("1555");
//        mapper.insert(user);

        for (int i = 0; i < 100; i++) {
            stringRedisTemplate.opsForValue().set("哈哈"+i, "1224"+i);
        }

        System.out.println(stringRedisTemplate.opsForValue().get("哈哈"));
//        System.err.println(stringRedisTemplate.opsForValue().get("test1"));
//        redisTemplate.opsForValue().set("hehe","14444");
//        Object hehe = redisTemplate.opsForValue().get("hehe");
//        System.out.println(hehe);

    }

}
