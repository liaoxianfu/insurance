package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.Recommendation;
import com.liao.insurance.service.IRecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  智能报价控制器
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@RestController
@RequestMapping("/insurance/recommendation")
@Api(tags = "2.1",description = "智能报价增删改查",value = "智能报价增删改查")
public class RecommendationController {

    @Resource
    private IRecommendationService recommendationService;

    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @ApiOperation(value = "添加智能报价",notes = "不需要添加id参数")
    @PostMapping("/")
    public Object addRecommendation(Recommendation recommendation) throws Exception{
        ModelMap model = new ModelMap();
        if (recommendation.getCarId() == null ||
                recommendation.getInsuranceId() == null ||
                recommendation.getLevel() == null){
            model.addAttribute("info", "数据不能为空");
            return JSON.toJSONString(model);
        }
        if (recommendationService.addRecommendation(recommendation) == false){
            model.addAttribute("info", "添加智能报价失败");
            return JSON.toJSONString(model);
        }else {
            model.addAttribute("info", "添加智能报价成功");
            return JSON.toJSONString(model);
        }
    }

    @ApiOperation(value = "修改推荐等级", notes = "参数：车辆型号id和保险id")
    @PutMapping("/")
    public Object updateLevel(Recommendation recommendation) throws Exception{
        ModelMap model = new ModelMap();
        if (recommendationService.updateLevel(recommendation) == false){
            model.addAttribute("info", "修改推荐等级失败");
        }else {
            model.addAttribute("info", "修改推荐等级成功");
        }
        return JSON.toJSONString(model);
    }

    @ApiOperation(value = "删除智能报价", notes = "参数：车辆型号id和保险id")
    @DeleteMapping("/")
    public Object delRecommendation(Integer carId, Integer insuranceId) throws Exception{
        ModelMap model = new ModelMap();
        if (recommendationService.delRecommendationByCarIdAndInsuranceId(carId, insuranceId) == false){
            model.addAttribute("info", "删除智能报价失败");
        }else {
            model.addAttribute("info", "删除智能报价成功");
        }
        return JSON.toJSONString(model);
    }

    @ApiOperation(value = "查询智能报价", notes = "参数：车辆型号id和保险id")
    @GetMapping("/")
    public Object findRecommendation(Integer carId, Integer insuranceId) throws Exception{
        ModelMap model = new ModelMap();
        Recommendation recommendation = new Recommendation();
        recommendation = recommendationService.findByCarIdAndInsuranceId(carId, insuranceId);
        if (recommendation == null){
            model.addAttribute("info", "null");
            return JSON.toJSONString(model);
        }else {
            return JSON.toJSONString(recommendation);
        }
    }
}
