package com.liao.insurance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Insurance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保险id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 保险名称
     */
    private String insuranceName;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 保险花费金额
     */
    private Double money;

    /**
     * 保期(多少天)
     */
    private Integer days;

    /**
     * 保险介绍
     */
    private String description;

    /**
     * 车主的汽车信息id
     */
    private Integer carInfoId;



}
