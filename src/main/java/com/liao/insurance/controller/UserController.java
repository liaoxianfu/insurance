package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.User;
import com.liao.insurance.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@RestController
@RequestMapping("/insurance/user")
@Api(tags = "2.2",description = "用户增删改查",value = "用户增删改查")
public class UserController {

    @Resource
    private IUserService userService;

    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @ApiOperation(value = "添加用户",notes = "不需要添加id参数")
    @PostMapping("/")
    public Object addUser(User user) throws Exception{
        ModelMap model = new ModelMap();
        if (user.getUsername() == null || user.getPassword() == null){
            model.addAttribute("info", "用户名和密码不能为空");
            return JSON.toJSONString(model);
        }
        if (userService.addUser(user) == false){
            model.addAttribute("info", "添加用户失败");
        }else {
            model.addAttribute("info", "添加用户成功");
        }
        return JSON.toJSONString(model);
    }

    @ApiOperation(value = "查找用户", notes = "参数：账号，密码")
    @GetMapping("/")
    public Object findUser(String username, String password) throws Exception{
        ModelMap model = new ModelMap();
        User user = userService.findUserByUserNameAndPassWord(username, password);
        if (user == null){
            model.addAttribute("info", "null");
            return JSON.toJSONString(model);
        }
        return JSON.toJSONString(user);
    }

    @ApiOperation(value = "修改密码", notes = "参数：账号，旧密码，新密码")
    @PutMapping("/password")
    public Object updatePassWord(String username, String password_old, String password_new) throws Exception{
        ModelMap model = new ModelMap();
        if (userService.updatePassword(username, password_old, password_new) == false){
            model.addAttribute("info", "修改密码失败");
        }else {
            model.addAttribute("info", "修改密码成功");
        }
        return JSON.toJSONString(model);
    }

    @ApiOperation(value = "修改地址", notes = "参数：账号，新地址")
    @PutMapping("/address")
    public Object updateAddress(String username, String address) throws Exception{
        ModelMap model = new ModelMap();
        if (userService.updateAddress(username, address) == false){
            model.addAttribute("info", "修改地址失败");
        }else {
            model.addAttribute("info", "修改地址成功");
        }
        return JSON.toJSONString(model);
    }

    @ApiOperation(value = "修改车辆型号", notes = "参数：账号，新车辆型号")
    @PutMapping("/carId")
    public Object updateCarId(String username, Integer carId) throws Exception{
        ModelMap model = new ModelMap();
        if (userService.updateCarId(username, carId) == false){
            model.addAttribute("info", "修改车辆型号失败");
        }else {
            model.addAttribute("info", "修改车辆型号成功");
        }
        return JSON.toJSONString(model);
    }

    @ApiOperation(value = "修改手机号", notes = "参数：账号，新手机号")
    @PutMapping("/phoneNumber")
    public Object updatePhoneNumber( String username, String phoneNumber) throws Exception{
        ModelMap model = new ModelMap();
        if (userService.updatePhoneNumber(username, phoneNumber) == false){
            model.addAttribute("info", "修改手机号失败");
        }else {
            model.addAttribute("info", "修改手机号成功");
        }
        return JSON.toJSONString(model);
    }
}
