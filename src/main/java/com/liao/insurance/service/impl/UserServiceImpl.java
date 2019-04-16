package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.User;
import com.liao.insurance.mapper.UserMapper;
import com.liao.insurance.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("username", user.getUsername());
        if (userMapper.selectOne(qw) != null){
            return false;
        }else {
            userMapper.insert(user);
            return true;
        }
    }

    @Override
    public boolean updatePassword(String username, String password_old, String password_new) {
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("username", username);
        User user = userMapper.selectOne(qw);
        if (user == null){
            return false;
        }else {
            if (user.getPassword().equals(password_old) == false){
                return false;
            }else {
                user.setPassword(password_new);
                userMapper.update(user, qw);
                return true;
            }
        }
    }

    @Override
    public boolean updateAddress(String username, String address) {
        User user = new User();
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("username", username);
        user = userMapper.selectOne(qw);
        if (user == null){
            return false;
        }else {
            user.setAddress(address);
            userMapper.update(user, qw);
            return true;
        }
    }

    @Override
    public boolean updateCarId(String username, Integer carId) {
        User user = new User();
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("username", username);
        user = userMapper.selectOne(qw);
        if (user == null){
            return false;
        }else {
            user.setCarId(carId);
            userMapper.update(user, qw);
            return true;
        }
    }

    @Override
    public boolean updatePhoneNumber(String username, String phoneNumber) {
        User user = new User();
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("username", username);
        user = userMapper.selectOne(qw);
        if (user == null){
            return false;
        }else {
            user.setPhoneNumber(phoneNumber);
            userMapper.update(user, qw);
            return true;
        }
    }

    @Override
    public User findUserByUserNameAndPassWord(String username, String password) {
        Map<String, String> selectMap = new HashMap<String, String>();
        selectMap.put("username", username);
        selectMap.put("password", password);
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.allEq(selectMap);
        return userMapper.selectOne(qw);
    }
}
