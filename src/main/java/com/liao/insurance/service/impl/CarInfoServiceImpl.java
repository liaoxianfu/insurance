package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.CarInfo;
import com.liao.insurance.mapper.CarInfoMapper;
import com.liao.insurance.service.ICarInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 车主通过在APP内选择Car表中的汽车，记录自己汽车的情况
 * 服务实现类
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
    public int addCarInfo(CarInfo carInfo) {
        return carInfoMapper.insert(carInfo);
    }

    @Override
    public List<CarInfo> getCarInfoListByUserId(Integer id) {
        QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<CarInfo>().eq("user_id", id);
        return carInfoMapper.selectList(queryWrapper);
    }

    @Override
    public int updateCarInfo(CarInfo carInfo) {
        QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<CarInfo>().eq("user_id", carInfo.getUserId());
        return carInfoMapper.update(carInfo,queryWrapper);
    }

    @Override
    public int deleteCarInfoById(Integer id) {
        QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<CarInfo>().eq("id", id);
        return carInfoMapper.delete(queryWrapper);
    }
}
