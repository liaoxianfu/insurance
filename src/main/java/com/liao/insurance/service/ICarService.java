package com.liao.insurance.service;

import com.liao.insurance.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-13
 */
public interface ICarService extends IService<Car> {
    /**
     * 添加新的汽车型号
     * @param car 汽车对象
     * @return 插入更改的数目
     */
    int addCar(Car car);

}
