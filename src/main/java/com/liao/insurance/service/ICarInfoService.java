package com.liao.insurance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liao.insurance.entity.CarInfo;

import java.util.List;

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
     * @param carInfo 汽车对象
     * @return 状态码
     */
    int addCarInfo(CarInfo carInfo);

    /**
     * 通过用户的id获取拥有的车辆
     * @param id id
     * @return list
     */
    List<CarInfo> getCarInfoListByUserId(Integer id);

    /**
     * 通过用户id更新自己汽车的数据
     * 注： 用户没有使用权限 ，仅供管理人员通过用户的申诉使用
     * @param carInfo carInfo
     * @return list
     */
    int updateCarInfo(CarInfo carInfo);

    /**
     *  通过用户id删除自己汽车的数据
     *  注： 用户没有使用权限 ，仅供管理人员通过用户的申诉使用
     * @param id 用户id
     * @return 状态值
     */
    int deleteCarInfoById(Integer id);


}
