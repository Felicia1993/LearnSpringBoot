package com.springboot.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 技术方案的缺陷：
 * 需要频繁的修改redis，耗费cpu，高并发修改redis会导致cpu100%
 */
@RestController
@Slf4j
public class ViewController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/view")
    public void view(Integer id) {
        String key = "article" + id;
        int n = 0;
        this.stringRedisTemplate.opsForValue().increment(key,n);
        log.info("key = {}, 阅读量为{}", key,n);
    }
}
