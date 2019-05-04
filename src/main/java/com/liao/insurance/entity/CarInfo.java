package com.liao.insurance.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 顾客的汽车类型
 * </p>
 *
 * @author liao
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class CarInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 汽车名称
     */
    private String carName;

    /**
     * 发动机型号
     */
    private String engineNumber;

    private Double price;

    /**
     * 制造商
     */
    private String productionCompany;
    /**
     * 车主ID
     */
    private Integer userId;

}
