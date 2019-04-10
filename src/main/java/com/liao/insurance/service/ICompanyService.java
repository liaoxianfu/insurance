package com.liao.insurance.service;

import com.liao.insurance.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公司Service层
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
public interface ICompanyService extends IService<Company> {
    /**
     * 添加公司
     *
     * @param company 公司对象
     * @return 状态值 1 成功 0 创建失败 -1 已经存在该公司
     */
    int addCompany(Company company);

    /**
     * 获取所有的公司列表
     *
     * @return list
     */
    List<Company> getAllCompany();


}
