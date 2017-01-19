package com.muy.common.xml;



import com.muy.common.utils.XmlUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanglikai on 2017/1/19.
 */
public class XmlExample {
    public static void createXmlString4Object() {
        System.out.println(">>>>> Bean→Xml string start <<<<<");

        String value = XmlUtil.createXmlString(new Pay());
        System.out.println(value);

        System.out.println(">>>>> Bean→Xml string  end  <<<<<");
    }

    public static void createXmlString4Map() {
        System.out.println(">>>>> Map→Xml string start <<<<<");

        Map<String, Object> maps = new HashMap<>();
        maps.put("appid", "wx2421b1c4370ec43b");
        maps.put("attach", "支付测试");
        maps.put("body", "APP支付测试");
        maps.put("mch_id", "10000100");

        String value = XmlUtil.createXmlString(maps);
        System.out.println(value);

        System.out.println(">>>>> Map→Xml string  end  <<<<<");
    }

    public static void createXmlMap() {
        System.out.println(">>>>> Xml string→Map start <<<<<");

        String rootXml = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[APP]]></trade_type>\n" +
                "</xml>";
        Map<String, Object> xml2Map = XmlUtil.createXmlMap(rootXml);
        System.out.println(xml2Map);

        System.out.println(">>>>> Xml string→Map  end  <<<<<");
    }

    public static void createXmlClass() {
        System.out.println(">>>>> Xml string→Bean start <<<<<");

        String rootXml = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[APP]]></trade_type>\n" +
                "</xml>";
        Response response = XmlUtil.createXmlClass(rootXml, Response.class);
        System.out.println(response);

        System.out.println(">>>>> Xml string→Bean  end  <<<<<");
    }

    public static void main(String[] args) {
        // 1.Bean→Xml string
        createXmlString4Object();
        System.out.println();

        // 2.Map→Xml string
        createXmlString4Map();
        System.out.println();

        // 3.Xml string→Map
        createXmlMap();
        System.out.println();

        // 4.Xml string→Bean
        createXmlClass();
        System.out.println();
    }
}
