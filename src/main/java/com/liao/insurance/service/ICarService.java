package com.liao.insurance.service;

import com.liao.insurance.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-13
 */
public interface ICarService extends IService<Car> {
    /**
     * 添加新的汽车型号
     *
     * @param car 汽车对象
     * @return 插入更改的数目
     */
    int addCar(Car car);

    /**
     * 获取所有的汽车对象
     *
     * @return list
     */
    List<Car> getAllCars();

    /**
     * 通过汽车名获取汽车对象
     *
     * @param carName 汽车名
     * @return 汽车对象
     */
    Car getCarByCarName(String carName);

    /**
     * 通过汽车的生产公司获取汽车
     *
     * @param productCompany 公司名称
     * @return 汽车列表
     */
    List<Car> getCarByProductCompany(String productCompany);

    /**
     * 通过汽车的价格区间获取汽车对象
     * @param min 最低
     * @param max 最高
     * @return 汽车列表
     */
    List<Car> getCarByPrice(double min, double max);

    /**
     * 更新汽车信息
     * @param car 汽车对象
     * @return 状态码
     */
    int updateCar(Car car);

}
