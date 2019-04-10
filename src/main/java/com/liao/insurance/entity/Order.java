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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 保单号(UUID)
     */
    private String insuranceNumber;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 保险id
     */
    private Integer insuranceId;

    private Integer userId;

    /**
     * 剩余天数
     */
    private Integer residual;


}
