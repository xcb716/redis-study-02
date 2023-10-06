package com.redis.study02;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.SubTypeValidator;
import com.redis.study02.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

/*
 * @author: Abin
 * @date: 2023/10/5 13:10
 * @description:
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // JSON 工具
    private static final ObjectMapper mapper = new ObjectMapper();

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

    @Test
    void testStringTemplate() throws JsonProcessingException {
        // 准备对象
        User user = new User("虎哥", 88);
        // 手动序列化
        String jsonString = mapper.writeValueAsString(user);
        // 写入一条数据到redis
        stringRedisTemplate.opsForValue().set("user:200", jsonString);
        // 读取刚刚写入的数据
        String val = stringRedisTemplate.opsForValue().get("user:200");
        // 反序列化
        User user1 = mapper.readValue(val, User.class);
        System.out.println("user1 = " + user1);
    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:400", "name", "虎哥");
        stringRedisTemplate.opsForHash().put("user:400", "age", "900");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println("entries = " + entries);
    }
}
