package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.Orders;
import com.liao.insurance.service.IOrdersService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  订单控制器
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@RestController
@RequestMapping("/insurance/orders")
public class OrdersController {

    @Resource
    private IOrdersService ordersService;

    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @PostMapping("/")
    public Object addOrder(@RequestBody Orders orders) throws Exception{
        ModelMap model = new ModelMap();
        if (orders.getInsuranceNumber() == null ||
                orders.getCompanyId() == null ||
                orders.getInsuranceId() == null ||
                orders.getResidual() == null ||
                orders.getUserId() == null){
            model.addAttribute("info", "数据不能为空");
            return JSON.toJSON(model);
        }
        boolean code = ordersService.addOrder(orders);
        logger.debug("code ---> {}", code);
        if (code == false){
            model.addAttribute("info", "添加订单失败");
        }else {
            model.addAttribute("info", "添加订单成功");
        }
        return JSON.toJSON(model);
    }

    @GetMapping("/{user_id}")
    public Object findAllByUserId(@PathVariable Integer user_id){
        ModelMap model = new ModelMap();
        List<Orders> list = ordersService.findAllByUserId(user_id);
        if (list == null || list.isEmpty()){
            model.addAttribute("info", "null");
            return JSON.toJSON(model);
        }else {
            return JSON.toJSON(list);
        }
    }

    /**
     * 根据用户id和订单状态查询订单
     * @param user_id
     * @param status
     * @return
     */
    @GetMapping("/{user_id}/{status}")
    public Object findAllByUserIdAndStatus(@PathVariable Integer user_id, @PathVariable Integer status){
        ModelMap model = new ModelMap();
        List<Orders> list = ordersService.findAllByUserIdAndStatus(user_id, status);
        if (list == null || list.isEmpty()){
            model.addAttribute("info", "null");
            return JSON.toJSON(model);
        }else {
            return JSON.toJSON(list);
        }
    }

    /**
     * 根据订单UUID修改剩余天数
     * @param insuranceNumber
     * @param residual
     * @return
     */
    @PutMapping("/residual/{insuranceNumber}/{residual}")
    public Object updateResidualByUUID(@PathVariable String insuranceNumber, @PathVariable Integer residual){
        ModelMap model = new ModelMap();
        boolean code = ordersService.updateResidualByUUID(insuranceNumber, residual);
        if (code == false){
            model.addAttribute("info", "修改剩余天数失败");
            return JSON.toJSON(model);
        }else {
            model.addAttribute("info", "修改剩余天数成功");
            return JSON.toJSON(model);
        }
    }

    /**
     * 根据订单UUID修改订单状态
     * @param insuranceNumber
     * @param status
     * @return
     */
    @PutMapping("/status/{insuranceNumber}/{status}")
    public Object updateStatusByUUID(@PathVariable String insuranceNumber, @PathVariable Integer status){
        ModelMap model = new ModelMap();
        boolean code = ordersService.updateStatusByUUID(insuranceNumber, status);
        if (code == false){
            model.addAttribute("info", "修改订单状态失败");
            return JSON.toJSON(model);
        }else {
            model.addAttribute("info", "修改订单状态成功");
            return JSON.toJSON(model);
        }
    }
}
