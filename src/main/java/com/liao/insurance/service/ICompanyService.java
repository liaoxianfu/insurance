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
     * @return 公司列表
     */
    List<Company> getAllCompany();

    /**
     * 通过公司名称获取公司
     * @param companyName 公司名
     * @return 公司对象
     */
    Company getCompanyByName(String companyName);

    /**
     * 通过地址获取公司列表
     * @param address 地址
     * @return 公司列表
     */
    List<Company> getCompanyByAddress(String address);

    /**
     * 更新公司信息
     * @param company 公司对象
     * @return 状态值
     */
    int updateCompany(Company company);

    /**
     * 通过id删除公司
     * @param id id
     * @return 状态码
     */
    int deleteCompanyById(Integer id);


}
