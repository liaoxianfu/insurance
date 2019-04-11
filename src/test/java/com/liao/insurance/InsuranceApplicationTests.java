package com.liao.insurance;

import com.liao.insurance.service.IOrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InsuranceApplicationTests {

    @Resource
    IOrdersService iOrdersService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void te(){
        System.out.println(iOrdersService.findAllByUserIdAndStatus(1, 1));
        /*System.out.println(iOrdersService.findAll());
        iOrdersService.updateResidualByUUID("123",1);
        System.out.println(iOrdersService.findAll());*/
    }

}
