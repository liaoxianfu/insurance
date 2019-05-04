package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.CarInfo;
import com.liao.insurance.service.ICarInfoService;
import com.liao.insurance.utils.InfoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.liao.insurance.codeInfo.CodeInfo.*;
import static com.liao.insurance.codeInfo.MessageInfo.*;

/**
 * <p>
 * 汽车信息
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@RestController
@RequestMapping("/insurance/carInfo")
@Api(tags = "1.2", description = "用户汽车信息的增删改查",
        value = "用户汽车信息的增删改查")


public class CarInfoController {
    private static Logger logger = LoggerFactory.getLogger(CarInfoController.class);
    private final ICarInfoService carInfoService;


    @Autowired
    public CarInfoController(ICarInfoService carInfoService) {
        this.carInfoService = carInfoService;
    }


    @ApiOperation(value = "添加用户的汽车信息",
            notes = "不添加id参数，系统自动生成")
    @PostMapping("/")
    public Object addCarInfo(CarInfo carInfo) {
        logger.debug("post 接受的信息--->{}", carInfo);
        int code = carInfoService.addCarInfo(carInfo);
        return InfoUtils.postInfoProcess(code);
    }

    @ApiOperation(value = "通过用户id获取用户拥有的全部汽车信息",notes =
    "用户的id为必须选项"
    )
    @GetMapping("/")
    public Object getCarInfoByUserId(Integer userId) {
        logger.debug("用户id --->{}", userId);
        ModelMap modelMap = new ModelMap();
        if (userId == null) {
            modelMap.addAttribute("info", ARGS_ERROR_MESSAGE);
            modelMap.addAttribute("code", UN_KNOW_ERROR);
            return JSON.toJSON(modelMap);
        }
        List<CarInfo> userList = carInfoService.getCarInfoListByUserId(userId);
        if (userList.size()==0){
            modelMap.addAttribute("info",NO_FOUND_CAR_INFO_MESSAGE);
            modelMap.addAttribute("code",NO_EXIST);
            return JSON.toJSON(modelMap);
        }
        return JSON.toJSON(userList);
    }

    public Object updateCarInfo(CarInfo carInfo){
        int code = carInfoService.updateCarInfo(carInfo);
        ModelMap modelMap = new ModelMap();
        if (code==UPDATE_SUCCESS){
            modelMap.addAttribute("info","修改成功");
        }
        return JSON.toJSON(modelMap);

    }


}
