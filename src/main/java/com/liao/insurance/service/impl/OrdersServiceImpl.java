package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.Orders;
import com.liao.insurance.mapper.OrdersMapper;
import com.liao.insurance.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liao
 * @since 2019-04-11
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

    @Override
    public List<Orders> findAll() {
        return ordersMapper.selectList(null);
    }

    @Override
    public boolean updateResidualByUUID(String insuranceNumber, Integer residual) {
        Orders order_new = new Orders();
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.eq("insurance_number", insuranceNumber);
        if (ordersMapper.selectOne(qw) == null){
            return false;
        }else {
            order_new = ordersMapper.selectOne(qw);
            order_new.setResidual(residual);
            ordersMapper.update(order_new, qw);
            return true;
        }
    }

    @Override
    public List<Orders> findAllByUserId(Integer userId) {
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.eq("user_id", userId);
        return ordersMapper.selectList(qw);
    }

    @Override
    public boolean updateStatusByUUID(String insuranceNumber, Integer status) {
        Orders orders_new = new Orders();
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        qw.eq("insurance_number", status);
        if(ordersMapper.selectOne(qw) == null){
            return false;
        }else {
            orders_new = ordersMapper.selectOne(qw);
            orders_new.setStatus(status);
            ordersMapper.update(orders_new, qw);
            return true;
        }
    }

    @Override
    public List<Orders> findAllByUserIdAndStatus(Integer userId, Integer status) {
        QueryWrapper<Orders> qw = new QueryWrapper<Orders>();
        Map<String, Integer> selectMap = new HashMap<String, Integer>();
        selectMap.put("user_id", userId);
        selectMap.put("status", status);
        qw.allEq(selectMap);
        return ordersMapper.selectList(qw);
    }
}
