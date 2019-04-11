package com.liao.insurance.service;

import com.liao.insurance.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
public interface IUserService extends IService<User> {

    boolean addUser(User user);

    boolean updatePassword(String username, String password_old, String password_new);

    boolean updateAddress(String username, String address);

    boolean updateCarId(String username, Integer carId);

    boolean updatePhoneNumber(String username, String phoneNumber);

    User findUserByUserNameAndPassWord(String username, String password);

}
