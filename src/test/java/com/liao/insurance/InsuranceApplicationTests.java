package com.liao.insurance;

import com.liao.insurance.entity.Car;
import com.liao.insurance.service.ICarService;
import com.liao.insurance.service.IOrdersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InsuranceApplicationTests {

    @Resource
    IOrdersService iOrdersService;
    @Resource
    ICarService carService;

    @Test
    public void contextLoads() {
//        List<Car> carByProductCompany = carService.getCarByProductCompany(null);
//        System.err.println(carByProductCompany);
    }

    @Test
    public void te(){
//        System.out.println(iOrdersService.findAllByUserIdAndStatus(1, 1));
        /*System.out.println(iOrdersService.findAll());
        iOrdersService.updateResidualByUUID("123",1);
        System.out.println(iOrdersService.findAll());*/
    }

}
