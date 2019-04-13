package com.liao.insurance.service;

import com.liao.insurance.entity.CarInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
public interface ICarInfoService extends IService<CarInfo> {
    /**
     * 添加汽车
     * @param car 汽车对象
     * @return 状态码
     */
    int addCar(CarInfo car);

}
