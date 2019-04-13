package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.Car;
import com.liao.insurance.mapper.CarMapper;
import com.liao.insurance.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liao
 * @since 2019-04-13
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

    @Resource
    private CarMapper carMapper;
    @Override
    public int addCar(Car car) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<Car>()
                .eq("car_name", car.getCarName())
                .eq("product_company", car.getProductCompany());
        Integer res = carMapper.selectCount(queryWrapper);
        if (res==0){
            return carMapper.insert(car);
        }
        return 0;
    }

}
