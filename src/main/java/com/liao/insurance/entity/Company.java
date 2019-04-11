package com.liao.insurance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@ToString
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 保险公司id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 保险公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 法定代表人
     */
    private String master;

    /**
     * 联系方式
     */
    private String tel;


}
