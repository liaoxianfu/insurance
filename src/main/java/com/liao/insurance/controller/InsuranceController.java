package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.Insurance;
import com.liao.insurance.service.IInsuranceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.liao.insurance.codeInfo.CodeInfo.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@RestController
@RequestMapping("/insurance/insurance")
@Api(tags = "1.3", description = "保险的增删查改", value = "保险的增删查改")
public class InsuranceController {

    private static Logger logger = LoggerFactory.getLogger(InsuranceController.class);
    private final IInsuranceService insuranceService;


    @Autowired
    public InsuranceController(IInsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * 添加保险业务
     *
     * @param insurance
     * @return
     */
    @PostMapping("/")
    @ApiOperation(value = "添加新的保险业务", notes = "不需要添加id参数")
    public Object addInsurance(Insurance insurance) {
        ModelMap modelMap = new ModelMap();
        int code = insuranceService.addInsurance(insurance);
        logger.debug("添加保险返回值----{}", insurance);
        if (code == ADD_SUCCESS) {
            modelMap.addAttribute("info", "添加成功");
        } else {
            modelMap.addAttribute("info", "添加失败");
        }
        modelMap.addAttribute("code", code);
        return JSON.toJSON(modelMap);
    }


    private Object noInsuranceProcess() {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("info", "暂无数据");
        modelMap.addAttribute("code", NO_EXIST);
        return JSON.toJSON(modelMap);
    }


    private Object sizeCheck(List insuranceList) {
        if (insuranceList.size() != 0) {
            return JSON.toJSON(insuranceList);
        }
        return noInsuranceProcess();
    }


    private Object getAllInsurance() {
        List<Insurance> insuranceList = insuranceService.getAllInsurance();
        return sizeCheck(insuranceList);
    }

    private Object getInsuranceByName(String insuranceName) {
        Insurance insuranceByName = insuranceService.getInsuranceByName(insuranceName);
        if (insuranceByName != null) {
            return JSON.toJSON(insuranceByName);
        }
        return noInsuranceProcess();
    }

    private Object getInsuranceByCompanyId(Integer id) {
        List<Insurance> insuranceList = insuranceService.getInsuranceByCompanyId(id);
        return sizeCheck(insuranceList);
    }


    @GetMapping("/")
    @ApiOperation(value = "通过不同的参数获取保险列表")

    public Object getInsuranceByArgs(String insuranceName, Integer companyId) {
        logger.debug("InsuranceName={}，companId={}", insuranceName, companyId);
        if (insuranceName != null) {
            return getInsuranceByName(insuranceName);
        } else if (companyId != null) {
            return getInsuranceByCompanyId(companyId);
        } else {
            return getAllInsurance();
        }
    }

    @PutMapping("/")
    @ApiOperation(value = "通过id更新保险")
    public Object putInsuranceById(Insurance insurance) {
        ModelMap modelMap = new ModelMap();
        int i = insuranceService.updateInsuranceById(insurance);
        modelMap.addAttribute("code", i);
        if (i == 1) {
            modelMap.addAttribute("info", "添加成功");
        } else {
            modelMap.addAttribute("info", "添加失败");
        }
        modelMap.addAttribute("code", i);
        return JSON.toJSON(modelMap);

    }

    @DeleteMapping("/")
    @ApiOperation(value = "通过id删除数据")
    public Object deleteInsuranceById(Integer id) {
        ModelMap modelMap = new ModelMap();
        int code = insuranceService.deleteInsuranceById(id);
        modelMap.addAttribute("code", code);
        if (code == DELETE_SUCCESS) {
            modelMap.addAttribute("info", "删除成功");
        } else {
            modelMap.addAttribute("info", "删除失败");
        }
        return JSON.toJSON(modelMap);

    }

    @PostMapping("/advice")
    @ApiOperation(value = "通过汽车的价格推荐相应的保险")
    public Object findInsuranceListByCarPrice(double price) {
        List<Insurance> insuranceList = insuranceService.getInsuranceListByCarPrice(price);
        return JSON.toJSON(insuranceList);
    }


}
