package com.springboot.redis.service;

import com.springboot.redis.entity.User;
import com.springboot.redis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"user"})
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Cacheable(key="#id")
    public User findUserById(Integer id) {
        return this.userMapper.selectByPrimaryKeyId(id);
    }
    @CachePut(key="obj.id")
    public User updateUser(User obj) {
        this.userMapper.updateByPrimaryKeySelective(obj);
        return this.userMapper.selectByPrimaryKeyId(obj.getId());
    }
    @CacheEvict(key = "#id")
    public void deleteUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setDeleted((byte) 1);
        this.userMapper.updateByPrimaryKeySelective(user);
    }
}
