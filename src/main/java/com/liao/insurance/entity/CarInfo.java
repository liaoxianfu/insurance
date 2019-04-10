package com.liao.insurance.entity;

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
public class CarInfo implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
