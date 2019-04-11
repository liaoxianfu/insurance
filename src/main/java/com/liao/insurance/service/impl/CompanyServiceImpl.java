package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.Company;
import com.liao.insurance.mapper.CompanyMapper;
import com.liao.insurance.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.liao.insurance.codeInfo.CodeInfo.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {


    @Resource
    private CompanyMapper companyMapper;

    private static Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Override
    public int addCompany(Company company) {
        String companyName = company.getCompanyName();
        logger.debug("公司名称 {}", companyName);
        QueryWrapper<Company> companyQueryWrapper = new QueryWrapper<>();
        companyQueryWrapper.eq("company_name", companyName);
        Company company1 = companyMapper.selectOne(companyQueryWrapper);
        if (company1 == null) {
            int insert = companyMapper.insert(company);
            logger.debug("返回的值 {}", insert);
            if (insert == COMPANY_CREATE_SUCCESS) {
                return COMPANY_CREATE_SUCCESS;
            }
            return insert;
        } else {
            return COMPANY_EXITS;
        }
    }

    @Override
    public List<Company> getAllCompany() {
        return companyMapper.selectList(null);
    }

    @Override
    public Company getCompanyByName(String companyName) {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<Company>().eq("company_name", companyName);
        return companyMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Company> getCompanyByAddress(String address) {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<Company>().eq("address", address);
        return companyMapper.selectList(queryWrapper);
    }

    @Override
    public int updateCompany(Company company) {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<Company>().eq("company_name", company.getCompanyName());
        return companyMapper.update(company, queryWrapper);
    }

    @Override
    public int deleteCompanyById(Integer id) {
        return companyMapper.deleteById(id);
    }


}
