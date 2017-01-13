package com.muy.pay.controller;

import com.muy.pay.service.PayService;
import com.pingplusplus.model.Charge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yanglikai on 2017/1/12.
 */
@RestController
@RequestMapping(value = "/pay")
public class PayController {
    private final static String appId = "app_Sqb5u5aXDSS8nPSe";

    @Resource
    private PayService payService;

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    @ResponseBody
    public Charge charge(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return payService.createCharge(appId);
    }
}
