package com.redis.cluster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

public class Controller {
    @Autowired
    RedisTemplate redisTemplate;
    @GetMapping("/set")
    public void add(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("/get")
    public String add(String key) {
        return (String) this.redisTemplate.opsForValue().get(key);
    }
}
