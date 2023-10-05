package com.redis.study02;

import com.redis.study02.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/*
 * @author: Abin
 * @date: 2023/10/5 13:10
 * @description:
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        // 插入一条redis数据
        redisTemplate.opsForValue().set("hehe", "dema");
        // 获取刚刚插入的redis数据
        Object name = redisTemplate.opsForValue().get("hehe");
        System.out.println("德玛西亚：" + name);
    }

    @Test
    void testSaveData() {
        // 插入一条redis数据
        redisTemplate.opsForValue().set("user:100", new User("赵信",90));
        // 取出刚刚插入的redis数据
        User user = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println("user = " + user);
    }
}
