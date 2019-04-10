package com.liao.insurance.service;

import com.liao.insurance.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
public interface IOrdersService extends IService<Orders> {
    boolean addOrder(Orders orders);
}
