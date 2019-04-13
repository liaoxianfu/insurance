package com.liao.insurance.codeInfo;

public class CodeInfo {
    /**
     * 未知错误
     */

    public static int UN_KNOW_ERROR = -99;
    /**
     * 公司已经存在状态码
     */
    public static int COMPANY_EXITS = -1;


    /**
     * 公司创建成功状态码
     */
    public static int COMPANY_CREATE_SUCCESS = 1;

    /**
     * 没有查到相应的公司
     */
    public static int NO_COMPANY_EXITS = -2;


    /**
     * 公司修改成功状态码
     */
    public static int COMPANY_UPDATE_SUCCESS = 1;

    /**
     * 公司修改失败状态码
     */
    public static int COMPANY_UPDATE_ERROR = -3;

    /**
     * 公司删除成功状态码
     */
    public static int COMPANY_DELETE_SUCCESS = 1;

    /**
     *  公司删除失败状态码
     */
    public static int COMPANY_DELETE_ERROR = -4;


    /**
     * 汽车存在状态码
     */
    public static int CAR_EXIST = 0;

    /**
     * 汽车添加成功状态码
     */
    public static int CAR_CREATE_SUCCESS = 1;

    /**
     * 汽车添加失败状态码
     */
    public static int NO_CAR_EXIST = -1;

    public static int UPDATE_ERROR = 0;
    public static int UPDATE_SUCCESS = 1;
    public static int DELETE_SUCCESS = 1;
    public static int DELETE_ERROE = -1;

}
