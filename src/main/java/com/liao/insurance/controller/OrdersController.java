package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.Orders;
import com.liao.insurance.service.IOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static com.liao.insurance.codeInfo.CodeInfo.*;

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
@Api(tags = "2.0",description = "订单增删改查",value = "订单增删改查")
public class OrdersController {

    @Resource
    private IOrdersService ordersService;

    @ApiOperation(value = "添加订单",notes = "不需要添加id和insuranceNumber参数")
    @PostMapping("/")
    public Object addOrder(Orders orders) throws Exception{
        ModelMap model = new ModelMap();
        if (orders.getCompanyId() == null ||
                orders.getInsuranceId() == null ||
                orders.getResidual() == null ||
                orders.getUserId() == null){
            model.addAttribute("info", "数据不能为空");
            model.addAttribute("code", STATUS_ERROR);
            return JSON.toJSON(model);
        }

        //生成UUID并添加到该orders当中
        UUID uuid = UUID.randomUUID();
        orders.setInsuranceNumber(uuid.toString());

        boolean code = ordersService.addOrder(orders);
        if (code == false){
            model.addAttribute("info", "添加订单失败");
            model.addAttribute("code", ADD_ERROR);
        }else {
            model.addAttribute("info", "添加订单成功");
            model.addAttribute("code", ADD_SUCCESS);
        }
        return JSON.toJSON(model);
    }


    @ApiOperation(value = "根据用户id获取订单",notes = "参数需要放在请求地址后面，而不是ajax的请求参数中")
    @GetMapping("/{user_id}")
    public Object findAllByUserId(@PathVariable Integer user_id) throws Exception{
        ModelMap model = new ModelMap();
        List<Orders> list = ordersService.findAllByUserId(user_id);
        if (list == null || list.isEmpty()){
            model.addAttribute("info", "该用户不存在或没有任何订单");
            model.addAttribute("code", NO_EXIST);
            return JSON.toJSON(model);
        }else {
            model.addAttribute("info", list);
            model.addAttribute("code", GET_SUCCESS);
            return JSON.toJSON(model);
        }
    }

    /**
     * 根据用户id和订单状态查询订单
     * @param user_id
     * @param status
     * @return
     */
    @ApiOperation(value = "根据用户id和订单状态获取订单",notes = "参数需要放在请求地址后面，而不是ajax的请求参数中")
    @GetMapping("/{user_id}/{status}")
    public Object findAllByUserIdAndStatus(@PathVariable Integer user_id, @PathVariable Integer status) throws Exception{
        ModelMap model = new ModelMap();
        List<Orders> list = ordersService.findAllByUserIdAndStatus(user_id, status);
        if (list == null || list.isEmpty()){
            model.addAttribute("info", "没有查询到订单");
            model.addAttribute("code", NO_EXIST);
            return JSON.toJSON(model);
        }else {
            model.addAttribute("info", list);
            model.addAttribute("code", GET_SUCCESS);
            return JSON.toJSON(list);
        }
    }

    /**
     * 根据订单号修改剩余天数
     * @param insuranceNumber
     * @param residual
     * @return
     */
    @ApiOperation(value = "根据订单号修改剩余天数",notes = "参数需要放在请求地址后面，而不是ajax的请求参数中")
    @PutMapping("/residual/{insuranceNumber}/{residual}")
    public Object updateResidualByInsuranceNumber(@PathVariable String insuranceNumber, @PathVariable Integer residual) throws Exception{
        ModelMap model = new ModelMap();
        boolean code = ordersService.updateResidualByInsuranceNumber(insuranceNumber, residual);
        if (code == false){
            model.addAttribute("info", "修改剩余天数失败");
            model.addAttribute("code", UPDATE_ERROR);
            return JSON.toJSON(model);
        }else {
            model.addAttribute("info", "修改剩余天数成功");
            model.addAttribute("code", UPDATE_SUCCESS);
            return JSON.toJSON(model);
        }
    }

    /**
     * 根据订单号修改订单状态
     * @param insuranceNumber
     * @param status
     * @return
     */
    @ApiOperation(value = "根据订单号修改订单状态",notes = "参数需要放在请求地址后面，而不是ajax的请求参数中")
    @PutMapping("/status/{insuranceNumber}/{status}")
    public Object updateStatusByInsuranceNumber(@PathVariable String insuranceNumber, @PathVariable Integer status) throws Exception{
        ModelMap model = new ModelMap();
        boolean code = ordersService.updateStatusByInsuranceNumber(insuranceNumber, status);
        if (code == false){
            model.addAttribute("info", "修改订单状态失败");
            model.addAttribute("code", UPDATE_ERROR);
            return JSON.toJSON(model);
        }else {
            model.addAttribute("info", "修改订单状态成功");
            model.addAttribute("code", UPDATE_SUCCESS);
            return JSON.toJSON(model);
        }
    }
}
