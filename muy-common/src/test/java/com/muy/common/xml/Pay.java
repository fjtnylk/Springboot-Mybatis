package com.muy.common.xml;

/**
 * Created by yanglikai on 2017/1/19.
 */
public class Pay {
    private String appid;
    private String attach;
    private String body;
    private String mch_id;
    private String nonce_str;
    private String notify_url;
    private String out_trade_no;
    private String spbill_create_ip;
    private String total_fee;
    private String trade_type;
    private String sign;

    public Pay() {
        this.appid = "wx2421b1c4370ec43b";
        this.attach = "支付测试";
        this.body = "APP支付测试";
        this.mch_id = "10000100";
        this.nonce_str = "1add1a30ac87aa2db72f57a2375d8fec";
        this.notify_url = "http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php";
        this.out_trade_no = "1415659990";
        this.spbill_create_ip = "14.23.150.211";
        this.total_fee = "1";
        this.trade_type = "APP";
        this.sign = "0CB01533B8C1EF103065174F50BCA001";
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
