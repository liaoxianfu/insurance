package com.liao.insurance.service;

import com.liao.insurance.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-03
 */
public interface IUserService extends IService<User> {
    User getuserById(int id);

    User addUser(User user);

}
