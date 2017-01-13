package com.muy.pay.service.impl;

import com.muy.pay.demo.ChargeExample;
import com.muy.pay.service.PayService;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2017/1/12.
 */
@Service
public class PayServiceImpl implements PayService {
    private final static String apiKey = "sk_test_nD88u5P0KKO8DyXHy910uzvH";

    @Override
    public Charge createCharge(String appId) {
        // 设置 API Key
        Pingpp.apiKey = apiKey;

        // 设置私钥路径，用于请求签名
        Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIICXwIBAAKBgQDorVJo3HWSQNLV7V/gqlnRqk11iaC7+bKaBDHYHRZ4tSstweod\n" +
                "5Ey3XEXlyiPouj+2T1gYHc4/FDqQf0SolmWh8fM5l73cbfxx7W68SDQPaif9+Dtr\n" +
                "nhpNKwZhCIwXGoT+5o1wkleMeBToxhQQXqsrMtvYGskOOWiOzL368AUjWwIDAQAB\n" +
                "AoGBAOfzAFgwjbQCkytXu2CH8Yolr5ogXO+G+Bi5XccHGEO0txqiWPT7hc60Wam9\n" +
                "XRa/tIvVhQhljhkr3UX22UcGxsBHg3BJwo4XvXTXUGkhDWEeZh6xebXjSa48UJ97\n" +
                "s4C5bdISS0g9yfZuYe/y9VGbMA6x3ImVmP72YbJzb1keXcuhAkEA9v9LqYLCwQiu\n" +
                "rZ0dg6/nxFM/HLxnjA0/tjlTg2izFGJptMBewQaz4MC7bJHAqi/hU0YP0sbmq63i\n" +
                "LTVqrG6LpwJBAPEoZ/JDeH42+DlDJMnZ5c2VLx1bGBT/1WYigdF9PzPcedPg3w/M\n" +
                "btq5g8V7FWF/f1hGudZdA2WJ/P5yZgw7kS0CQQDIC5blMHG50+Vd2a1o1ZRZ7q5k\n" +
                "ALfAt45NcPqRqL+5l8dTTKaxoVMYiiCCWz322JafUlRIey2iCE/yG5xNEa9rAkEA\n" +
                "nQKOssfE8n/9muKhWB5bJT+FHlrJ0hpDKS08UlV2SeC5HP7inBmg7x0rsu6RHBpz\n" +
                "odjic7O5W2m84zk+QaaivQJBALtcvy9nWq3p+7VMtWzPiJ3ouhhfw08aHmeoJbvf\n" +
                "q8f1QGJtGDdITIrbkngxW4EZosqBW+/eXuoUw2AsNAgqTx0=\n" +
                "-----END RSA PRIVATE KEY-----\n";

        ChargeExample chargeExample = new ChargeExample(appId);
        return chargeExample.createCharge();
    }
}
