package com.liao.insurance.service.impl;

import com.liao.insurance.entity.Company;
import com.liao.insurance.mapper.CompanyMapper;
import com.liao.insurance.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
