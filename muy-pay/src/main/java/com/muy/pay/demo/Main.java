package com.muy.pay.demo;

import com.pingplusplus.Pingpp;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by yanglikai on 2017/1/11.
 */
public class Main {
    /**
     * Pingpp 管理平台对应的 API Key，api_key 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击管理平台右上角公司名称->开发信息-> Secret Key
     */
    private final static String apiKey = "sk_test_nD88u5P0KKO8DyXHy910uzvH";

    /**
     * Pingpp 管理平台对应的应用 ID，app_id 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击你创建的应用->应用首页->应用 ID(App ID)
     */
    private final static String appId = "app_Sqb5u5aXDSS8nPSe";

    /**
     * 设置请求签名密钥，密钥对需要你自己用 openssl 工具生成，如何生成可以参考帮助中心：https://help.pingxx.com/article/123161；
     * 生成密钥后，需要在代码中设置请求签名的私钥(rsa_private_key.pem)；
     * 然后登录 [Dashboard](https://dashboard.pingxx.com)->点击右上角公司名称->开发信息->商户公钥（用于商户身份验证）
     * 将你的公钥复制粘贴进去并且保存->先启用 Test 模式进行测试->测试通过后启用 Live 模式
     */

    // 你生成的私钥路径
    private final static String privateKeyFilePath = "muy_rsa_private_key.pem";

    public static void main(String[] args) throws Exception {

        // 设置 API Key
        Pingpp.apiKey = apiKey;

        // 设置私钥路径，用于请求签名
//        Pingpp.privateKeyPath = privateKeyFilePath;
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

        /**
         * 或者直接设置私钥内容
         Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
         "... 私钥内容字符串 ...\n" +
         "-----END RSA PRIVATE KEY-----\n";
         */


        // Charge 示例
        ChargeExample.runDemos(appId);

//        // Refund 示例
//        RefundExample.runDemos();
//
//        // RedEnvelope 示例
//        RedEnvelopeExample.runDemos(appId);
//
//        // Transfer 示例
//        TransferExample.runDemos(appId);
//
//        // Event 示例
//        EventExample.runDemos();
//
//        // Webhooks 验证示例
//        WebhooksVerifyExample.runDemos();
//
//        // 微信公众号 openid 相关示例
//        WxPubOAuthExample.runDemos(appId);
//
//        // 身份证银行卡信息认证接口
//        IdentificationExample.runDemos(appId);
//
//        // 批量付款示例
//        BatchTransferExample.runDemos(appId);
//
//        // 报关
//        CustomsExample.runDemos(appId);
    }

    private static SecureRandom random = new SecureRandom();

    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }

    public static int currentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
