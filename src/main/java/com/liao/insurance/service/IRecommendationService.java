package com.liao.insurance.service;

import com.liao.insurance.entity.Recommendation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
public interface IRecommendationService extends IService<Recommendation> {

    boolean addRecommendation(Recommendation recommendation);

    boolean updateLevel(Recommendation recommendation);

    boolean delRecommendationByCarIdAndInsuranceId(Integer carId, Integer insuranceId);

    Recommendation findByCarIdAndInsuranceId(Integer carId, Integer insuranceId);

}
