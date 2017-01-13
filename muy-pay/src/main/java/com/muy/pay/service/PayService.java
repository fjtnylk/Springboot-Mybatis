package com.muy.pay.service;

import com.pingplusplus.model.Charge;

/**
 * Created by yanglikai on 2017/1/12.
 */
public interface PayService {

    Charge createCharge(String appId);
}
