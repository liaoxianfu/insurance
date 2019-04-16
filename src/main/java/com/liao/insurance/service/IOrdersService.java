package com.liao.insurance.service;

import com.liao.insurance.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-11
 */
public interface IOrdersService extends IService<Orders> {

    boolean addOrder(Orders orders);

    List<Orders> findAll();

    boolean updateResidualByInsuranceNumber(String insuranceNumber, Integer residual);

    List<Orders> findAllByUserId(Integer userId);

    boolean updateStatusByInsuranceNumber(String insuranceNumber, Integer status);

    List<Orders> findAllByUserIdAndStatus(Integer userId, Integer status);

}