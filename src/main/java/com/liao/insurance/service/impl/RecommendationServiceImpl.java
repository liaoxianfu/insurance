package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.liao.insurance.entity.Recommendation;
import com.liao.insurance.mapper.RecommendationMapper;
import com.liao.insurance.service.IRecommendationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@Service
public class RecommendationServiceImpl extends ServiceImpl<RecommendationMapper, Recommendation> implements IRecommendationService {

    @Resource
    private RecommendationMapper recommendationMapper;

    @Override
    public boolean addRecommendation(Recommendation recommendation) {
        QueryWrapper<Recommendation> qw = new QueryWrapper<Recommendation>();
        Map<String, Integer> selectMap = new HashMap<String, Integer>();
        selectMap.put("car_id", recommendation.getCarId());
        selectMap.put("insurance_id", recommendation.getInsuranceId());
        qw.allEq(selectMap);
        if(recommendationMapper.selectOne(qw) != null){
            return false;
        }else {
            recommendationMapper.insert(recommendation);
            return true;
        }
    }

    @Override
    public boolean updateLevel(Recommendation recommendation) {
        Map<String, Integer> selectMap = new HashMap<String, Integer>();
        QueryWrapper<Recommendation> qw = new QueryWrapper<Recommendation>();
        selectMap.put("car_id", recommendation.getCarId());
        selectMap.put("insurance_id", recommendation.getInsuranceId());
        qw.allEq(selectMap);
        if (recommendationMapper.selectOne(qw) == null){
            return false;
        }else {
            recommendationMapper.update(recommendation, qw);
            return true;
        }
    }

    @Override
    public boolean delRecommendationByCarIdAndInsuranceId(Integer carId, Integer insuranceId) {
        Map<String, Integer> selectMap = new HashMap<String, Integer>();
        QueryWrapper<Recommendation> qw = new QueryWrapper<Recommendation>();
        selectMap.put("car_id", carId);
        selectMap.put("insurance_id", insuranceId);
        qw.allEq(selectMap);
        if (recommendationMapper.selectOne(qw) == null){
            return false;
        }else {
            recommendationMapper.delete(qw);
            return true;
        }
    }

    @Override
    public Recommendation findByCarIdAndInsuranceId(Integer carId, Integer insuranceId) {
        QueryWrapper<Recommendation> qw = new QueryWrapper<Recommendation>();
        Map<String, Integer> selectMap = new HashMap<String, Integer>();
        selectMap.put("car_id", carId);
        selectMap.put("insurance_id", insuranceId);
        qw.allEq(selectMap);
        return recommendationMapper.selectOne(qw);
    }
}
