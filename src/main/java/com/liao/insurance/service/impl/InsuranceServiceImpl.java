package com.liao.insurance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liao.insurance.entity.Insurance;
import com.liao.insurance.mapper.InsuranceMapper;
import com.liao.insurance.service.IInsuranceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  保险服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@Service
public class InsuranceServiceImpl extends ServiceImpl<InsuranceMapper, Insurance> implements IInsuranceService {

    @Resource
    private InsuranceMapper insuranceMapper;

    @Override
    public int addInsurance(Insurance insurance) {
        return insuranceMapper.insert(insurance);
    }

    @Override
    public List<Insurance> getAllInsurance() {
        return insuranceMapper.selectList(null);
    }

    @Override
    public Insurance getInsuranceByName(String insuranceName) {
        QueryWrapper<Insurance> queryWrapper = new QueryWrapper<Insurance>().eq("insurance_name", insuranceName);
        return insuranceMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Insurance> getInsuranceByCompanyId(Integer companyId) {
        QueryWrapper<Insurance> queryWrapper = new QueryWrapper<Insurance>().eq("company_id", companyId);
        return insuranceMapper.selectList(queryWrapper);
    }



    @Override
    public int updateInsuranceById(Insurance insurance) {
        return insuranceMapper.updateById(insurance);
    }

    @Override
    public int deleteInsuranceById(Integer id) {
        return insuranceMapper.deleteById(id);
    }
}
