package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.CarInfo;
import com.liao.insurance.mapper.CarInfoMapper;
import com.liao.insurance.service.ICarInfoService;
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
 * @since 2019-04-10
 */
@Service
public class CarInfoServiceImpl extends ServiceImpl<CarInfoMapper, CarInfo> implements ICarInfoService {

    @Resource
    private CarInfoMapper carInfoMapper;

    @Override
    public int addCar(CarInfo car) {
        // 判断是否已经存入了该种汽车
        new QueryWrapper<CarInfo>().eq("car_name",car.getCarName()).eq("engine_number",car.getCarName());
        return 0;
    }
}
