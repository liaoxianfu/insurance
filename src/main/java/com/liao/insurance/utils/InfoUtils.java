package com.liao.insurance.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.ModelMap;

import static com.liao.insurance.codeInfo.CodeInfo.ADD_SUCCESS;
import static com.liao.insurance.codeInfo.MessageInfo.ADD_ERROR_MESSAGE;
import static com.liao.insurance.codeInfo.MessageInfo.ADD_SUCCESS_MESSAGE;

/**
 * @author liao
 */
public class InfoUtils {
    public static Object postInfoProcess(int code){
        ModelMap modelMap = new ModelMap();
        if (code == ADD_SUCCESS) {
            modelMap.addAttribute("info", ADD_SUCCESS_MESSAGE);
        } else {
            modelMap.addAttribute("info", ADD_ERROR_MESSAGE);
        }
        modelMap.addAttribute("code", code);
        return JSON.toJSON(modelMap);
    }
}
