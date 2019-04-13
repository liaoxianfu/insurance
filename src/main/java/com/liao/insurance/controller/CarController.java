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
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.liao.insurance.codeInfo.CodeInfo.*;

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
@Api(tags = "1.0", description = "汽车种类增删改查", value = "汽车种类增删改查")
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
    @ApiOperation(value = "添加汽车", notes = "id参数不用添加，自动生成")
    public Object addCar(Car car) {
        logger.debug("car --->{}", car);
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

    /**
     * 查询没有数据时返回的信息
     *
     * @return json
     */
    private Object noCarProcess() {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("info", "暂无数据");
        modelMap.addAttribute("code", NO_CAR_EXIST);
        return JSON.toJSON(modelMap);
    }

    /**
     * 获取所有的汽车
     *
     * @return cars
     */
    private Object getAllCars() {
        List<Car> cars = carService.getAllCars();
        if (cars.size() != 0) {
            return JSON.toJSON(cars);
        }
        return noCarProcess();
    }

    private Object getCarByName(String carName) {
        Car car = carService.getCarByCarName(carName);
        if (car != null) {
            return JSON.toJSON(car);
        }
        return noCarProcess();
    }

    /**
     * 通过不同的参数获取汽车
     *
     * @param carName        汽车名
     * @param productCompany 生产公司
     * @return 汽车列表或者对象
     */
    @GetMapping("/")
    @ApiOperation(value = "通过不同的参数，获取对应的数据值", notes = "如果都不写默认返回所有的数据")
    public Object getCar(String carName, String productCompany, Double minPrice, Double maxPrice) {

        // 判断所有的参数是否都是null 只要有一个不是，就改成false
        boolean isAllNull = true;
        if (carName != null) {
//            精确数据 直接返回
            return getCarByName(carName);
        }
        List<Car> res = null;
        if (productCompany != null) {
            isAllNull = false;
//          进行结果集赋值
            res = carService.getCarByProductCompany(productCompany);
        }
        if (minPrice != null || maxPrice != null) {
            isAllNull = false;
            if (minPrice == null) {
                minPrice = 0.0;
            }
            if (maxPrice == null) {
                maxPrice = Double.MAX_VALUE;
            }
            List<Car> carByPrice = carService.getCarByPrice(minPrice, maxPrice);
            if (res == null) {
//                进行结果赋值
                res = carByPrice;
            } else {
//                求交集
                res.retainAll(carByPrice);
            }
        }
//  判断是否创建了结果集以及结果集是否有数据
        if (res != null && res.size() != 0) {
            return JSON.toJSON(res);
        }
// 参数都为空
        if (isAllNull) {
            return getAllCars();
        }
// 没有满足的条件
        return noCarProcess();
    }

    /**
     * 更新汽车信息
     *
     * @param car 汽车
     * @return json
     */
    @PutMapping("/")
    @ApiOperation(value = "更新汽车信息", notes = "返回的状态值为1 表示成功 其他代表失败")
    public Object UpdateCar(Car car) {
        ModelMap modelMap = new ModelMap();
        int code = carService.updateCar(car);
        if (code == UPDATE_SUCCESS) {
            modelMap.addAttribute("info", "修改成功");
        } else {
            modelMap.addAttribute("info", "修改失败");
        }
        modelMap.addAttribute("code", code);
        return JSON.toJSON(modelMap);
    }
    @ApiOperation(value = "删除汽车信息", notes = "返回的状态值为1 表示成功 其他代表失败")
    @DeleteMapping("/{id}")
    public Object DeleteById(@PathVariable Integer id){
        ModelMap modelMap = new ModelMap();
        int code = DELETE_ERROE;
        boolean b = carService.removeById(id);
        if (b){
            modelMap.addAttribute("info","删除成功");
            code=DELETE_SUCCESS;
        }else {
            modelMap.addAttribute("info","删除失败");
        }
        modelMap.addAttribute("code",code);
        return JSON.toJSON(modelMap);

    }


}
