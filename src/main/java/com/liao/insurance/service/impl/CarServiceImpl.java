package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.Car;
import com.liao.insurance.mapper.CarMapper;
import com.liao.insurance.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
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
        if (res == 0) {
            return carMapper.insert(car);
        }
        return 0;
    }

    @Override
    public List<Car> getAllCars() {
        return carMapper.selectList(null);
    }

    @Override
    public Car getCarByCarName(String carName) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<Car>().eq("car_name", carName);
        return carMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Car> getCarByProductCompany(String productCompany) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<Car>().eq("product_company", productCompany);
        return carMapper.selectList(queryWrapper);
    }

    @Override
    public List<Car> getCarByPrice(double min, double max) {
        QueryWrapper<Car> q = new QueryWrapper<Car>().between("evaluate_price", min, max);
        return carMapper.selectList(q);
    }

    @Override
    public int updateCar(Car car) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<Car>().eq("car_name", car.getCarName());
        return carMapper.update(car, queryWrapper);
    }


}
