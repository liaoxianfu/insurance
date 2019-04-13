package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.battcn.swagger.properties.ApiDataType;
import com.battcn.swagger.properties.ApiParamType;
import com.liao.insurance.entity.Car;
import com.liao.insurance.service.ICarService;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.liao.insurance.codeInfo.CodeInfo.CAR_CREATE_SUCCESS;
import static com.liao.insurance.codeInfo.CodeInfo.CAR_EXIST;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liao
 * @since 2019-04-13
 */
@RestController
@RequestMapping("/insurance/car")
@Api(tags = "1.0",description = "汽车种类增删改查",value = "汽车种类增删改查")
public class CarController {
    private final ICarService carService;

    private static Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    /**
     * 添加car
     *
     * @param car 从表单接收的car对象
     * @return 状态码
     */
    @PostMapping("/")
    @ApiOperation(value = "添加汽车")
    public Object addCar(Car car) {
        logger.debug("car --->{}",car);
        int status = carService.addCar(car);
        logger.debug("汽车添加  code---->{}", status);
        ModelMap modelMap = new ModelMap();
        if (status == CAR_CREATE_SUCCESS) {
            modelMap.addAttribute("info", "添加成功");
        } else if (status == CAR_EXIST) {
            modelMap.addAttribute("info", "已经存在该类型的汽车");
        } else {
            modelMap.addAttribute("info", "添加失败");
        }
        modelMap.addAttribute("code", status);
        return JSON.toJSON(modelMap);
    }

}
