package com.liao.insurance.service.impl;

import com.liao.insurance.entity.User;
import com.liao.insurance.mapper.UserMapper;
import com.liao.insurance.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liao
 * @since 2019-04-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getuserById(int id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public User addUser(User user) {
        int insert = userMapper.insert(user);
        return user;
    }
}
