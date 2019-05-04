package com.liao.insurance.service;

import com.liao.insurance.entity.Insurance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
public interface IInsuranceService extends IService<Insurance> {

    /**
     * 添加新的保险类型
     * @param insurance 保险对象
     * @return
     */
    int addInsurance(Insurance insurance);

    /**
     * 获取所有的保险种类
     * @return
     */
    List<Insurance> getAllInsurance();

    /**
     * 通过保险名称获取保险对象
     * @param insuranceName 保险名称
     * @return
     */
    Insurance getInsuranceByName(String insuranceName);

    /**
     * 通过公司id 获取该公司的保险列表
     * @param companyId 公司id
     * @return
     */
    List<Insurance> getInsuranceByCompanyId(Integer companyId);



    /**
     * 更新保险
     * @param insurance 保险
     * @return
     */
    int updateInsuranceById(Insurance insurance);

    /**
     * 通过id 删除保险
     * @param id id
     * @return
     */
    int deleteInsuranceById(Integer id);



}
