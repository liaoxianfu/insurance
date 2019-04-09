package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.User;
import com.liao.insurance.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liao
 * @since 2019-04-03
 */
@RestController
@RequestMapping("/insurance/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/{id}")
    public Object getUser(@PathVariable Integer id){
//        System.out.println(id);
        User user = userService.getuserById(id);
        return JSON.toJSON(user);
    }

    @PostMapping("/")
    public Object addUser(User user){
        System.out.println(user);
        User addUser = userService.addUser(user);

        return JSON.toJSON(addUser);
    }








}
