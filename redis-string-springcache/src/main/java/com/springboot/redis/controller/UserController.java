package com.springboot.redis.controller;

import com.springboot.redis.entity.User;
import com.springboot.redis.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public UserVO findById(@PathVariable Integer id) {
        User user = this.userService.findUserById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
    @RequestMapping(value = "/updateUser")
    public void updateUser(@RequestBody UserVO obj) {
        User user = new User();
        BeanUtils.copyProperties(user, obj);
        userService.updateUser(user);
    }


}