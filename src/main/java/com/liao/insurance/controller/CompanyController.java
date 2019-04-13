package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.Company;
import com.liao.insurance.service.ICompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.acl.LastOwnerException;
import java.util.List;

import static com.liao.insurance.codeInfo.CodeInfo.*;

/**
 * <p>
 * 公司url
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@RestController
@RequestMapping("/insurance/company")
@Api(tags = "1.1",description = "公司增删改查",value = "公司增删改查")
public class CompanyController {

    private final ICompanyService companyService;

    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @ApiOperation(value = "添加公司",notes = "不需要添加id参数")
    @PostMapping("/")
    public Object addCompany(Company company) {
        ModelMap model = new ModelMap();
        // 返回不同的状态码
        int code = companyService.addCompany(company);

        logger.debug(company.toString());
        if (code == COMPANY_CREATE_SUCCESS) {
            model.addAttribute("info", "添加成功");
        } else if (code == COMPANY_EXITS) {
            model.addAttribute("info", "公司已经存在");
        } else {
            model.addAttribute("info", "添加失败");
        }
        logger.debug("状态码---->{}", code);
        model.addAttribute("code", code);
        logger.debug("返回的信息----> {}", model);
        return JSON.toJSON(model);
    }


    /**
     * 在判定查询对象不存在的情况下处理数据 返回相应的信息
     *
     * @return json
     */
    private Object noCompanyProcess() {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("info", "暂无数据");
        modelMap.addAttribute("code", NO_COMPANY_EXITS);
        return JSON.toJSON(modelMap);
    }

    /**
     * <p>
     * 获取所有的公司对象并转换成json数据<br/>
     * 如果查询的数据集为空，就返回 noCompanyProcess() 函数处理得到的信息
     * </p>
     *
     * @return 所有的公司对象的json信息
     */
    private Object getAllCompany() {
        List<Company> companyList = companyService.getAllCompany();
        if (companyList.size() == 0) {
            return noCompanyProcess();
        }
        return JSON.toJSON(companyList);
    }


    /**
     * <p>
     * 通过公司名称公司对象并转换成json数据<br/>
     * 如果查询的数据集为空，就返回 noCompanyProcess() 函数处理得到的信息
     * </p>
     *
     * @param companyName 公司名称
     * @return 公司对象json
     */
    private Object getCompanyByName(String companyName) {
        logger.debug("公司名----->{}", companyName);
        Company company = companyService.getCompanyByName(companyName);
        if (company == null) {
            return noCompanyProcess();
        }
        return JSON.toJSON(company);
    }

    /**
     * <p>
     * 通过地址获取所有的公司对象并转换成json数据<br/>
     * 如果查询的数据集为空，就返回 noCompanyProcess() 函数处理得到的信息
     * </p>
     *
     * @param address 地址
     * @return 公司对象列表json
     */
    private Object getCompanyByAddress(String address) {
        logger.debug("公司地址----->{}", address);
        List<Company> companyList = companyService.getCompanyByAddress(address);
        if (companyList.size() == 0) {
            return noCompanyProcess();
        }
        return JSON.toJSON(companyList);
    }

    /**
     * <p>
     *     通过不同的条件查询对应的公司
     * </p>
     *
     * @param companyName 公司名
     * @param address 地址
     * @return 公司集合或者对象 json
     */

    @ApiOperation(value = "通过不同的参数获取公司",
            notes = "如果不带参数就是请求所有的公司，以列表展示")
    @GetMapping("/")
    public Object getCompanyByArgs(String companyName, String address) {
        logger.debug("info---->公司名称：{},  地址： {}", companyName, address);
        if (companyName == null && address == null) {
            return getAllCompany();
        } else if (companyName != null) {
            return getCompanyByName(companyName);
        } else {
            return getCompanyByAddress(address);
        }
    }

    /**
     * 通过id查询对应的公司
     * @param id id
     * @return 公司对象
     */

    @ApiOperation(value = "通过id获取公司")
    @GetMapping("/{id}")
    public Object getCompanyById(@PathVariable Integer id) {
        Company company = companyService.getById(id);
        if (company==null){
           return noCompanyProcess();
        }
        return JSON.toJSON(company);
    }

    /**
     * 通过公司名称 修改公司的信息
     * @param company 公司对象
     * @return 修改信息
     */
    @ApiOperation("修改公司信息")
    @PutMapping("/")
    public Object updateCompany(Company company) {
        ModelMap modelMap = new ModelMap();
        int code = companyService.updateCompany(company);
        if (code == COMPANY_UPDATE_SUCCESS) {
            modelMap.addAttribute("info", "修改成功");
        } else {
            modelMap.addAttribute("info", "修改失败");
            code=COMPANY_UPDATE_ERROR;
        }
        modelMap.addAttribute("code", code);
        return JSON.toJSON(modelMap);
    }

    @ApiOperation(value = "通过id删除公司")
    @DeleteMapping("/{id}")
    public Object deleteCompanyById(@PathVariable int id){
        ModelMap modelMap = new ModelMap();
        int code = companyService.deleteCompanyById(id);
        if (code==COMPANY_DELETE_SUCCESS){
            modelMap.addAttribute("info","删除成功");
        }else {
            modelMap.addAttribute("info","删除失败");
            code = COMPANY_DELETE_ERROR;
        }
        modelMap.addAttribute("code",code);
        return JSON.toJSON(modelMap);
    }

}
