package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.Orders;
import com.liao.insurance.mapper.OrdersMapper;
import com.liao.insurance.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    @Resource
    OrdersMapper ordersMapper;

    @Override
    public boolean addOrder(Orders orders) {
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.eq("insurance_number", orders.getInsuranceNumber());
        if (ordersMapper.selectOne(qw) == null){
            ordersMapper.insert(orders);
            if (ordersMapper.selectOne(qw) == null){
                return false;
            }
            return true;
        }else {
            return false;
        }
    }
}
