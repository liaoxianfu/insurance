package com.liao.insurance.controller;


import com.alibaba.fastjson.JSON;
import com.liao.insurance.entity.Company;
import com.liao.insurance.service.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
public class CompanyController {

    private final ICompanyService companyService;

    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/")
    public Object addCompany(Company company) {
        ModelMap model = new ModelMap();
        // 返回不同的状态码
        int code = companyService.addCompany(company);

        logger.debug(company.toString());
        if (code == COMPANY_CREATE_SUCCESS) {
            model.addAttribute("info", "添加成功");
        }
        else if (code==COMPANY_EXITS){
            model.addAttribute("info","公司已经存在");
        }
        else {
            model.addAttribute("info", "添加失败");
        }
        logger.debug("状态码---->{}",code);
        model.addAttribute("code", code);
        logger.debug("返回的信息----> {}", model);
        return JSON.toJSON(model);
    }

    @GetMapping("/")
    public Object getAllCompany() {
        List<Company> companyList = companyService.getAllCompany();
        return JSON.toJSON(companyList);
    }

    @GetMapping("/{id}")
    public Object getCompanyById(@PathVariable Integer id) {
        Company company = companyService.getById(id);
        return JSON.toJSON(company);
    }


}
