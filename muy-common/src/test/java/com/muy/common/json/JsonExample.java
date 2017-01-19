package com.muy.common.json;


import com.muy.common.utils.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanglikai on 2017/1/17.
 */
public class JsonExample {
    public static void createJSONString4Map() {
        System.out.println(">>>>> Map→String <<<<<");

        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("userId", "10001");
        maps.put("userName", "yanglikai");
        String value = JsonUtil.createJSONString(maps);
        System.out.println(value);

        System.out.println(">>>>> Map→String <<<<<");
    }

    public static void createJSONString4Class() {
        System.out.println(">>>>> Class→String <<<<<");

        User user = new User();
        user.setId(20001);
        user.setName("yanglikai");
        String value = JsonUtil.createJSONString(user);
        System.out.println(value);

        System.out.println(">>>>> Class→String <<<<<");
    }

    public static void createJSONString4List() {
        System.out.println(">>>>> List→String <<<<<");
        User user = new User();
        user.setId(20001);
        user.setName("yanglikai");

        User user1 = new User();
        user1.setId(30001);
        user1.setName("yanglikai001");
        List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user1);

        String value = JsonUtil.createJSONString(users);
        System.out.println(value);
        System.out.println(">>>>> List→String <<<<<");
    }

    public static void createJSONMap4String() {
        System.out.println(">>>>> String→Map <<<<<");

        String mapValue = "{\"id\":\"10001\",\"name\":\"yanglikai\"}";
        Map<String, Object> map = JsonUtil.createJSONMap(mapValue);
        System.out.println(map);

        System.out.println(">>>>> String→Map <<<<<");
    }

    public static void createJSONClass4String() {
        System.out.println(">>>>> String→Class <<<<<");

        String value = "{\"userId\":\"10001\",\"userName\":\"yanglikai\"}";
        Person person = JsonUtil.createJSONClass(value, Person.class);
        System.out.println(person.toString());

        String userValue = "{\"id\":\"10001\",\"name\":\"yanglikai\"}";
        User user = JsonUtil.createJSONClass(userValue, User.class);
        System.out.println(user.toString());

        System.out.println(">>>>> String→Class <<<<<");
    }

    public static void createJSONList4Strig() {
        System.out.println(">>>>> String→List <<<<<");

        String value = "[{\"id\":20001,\"name\":\"yanglikai\"},{\"id\":30001,\"name\":\"yanglikai001\"}]";
        List<User> users = JsonUtil.createJSONList(value, User.class);
        for (User user : users) {
            System.out.println(user.toString());
        }

        System.out.println(">>>>> String→List <<<<<");
    }

    public static void main(String[] args) throws IOException {
        // 1.Map→String
        JsonExample.createJSONString4Map();
        System.out.println();

        // 2.Class→String
        JsonExample.createJSONString4Class();
        System.out.println();

        // 3.List→String
        JsonExample.createJSONString4List();
        System.out.println();

        // 4.String→Map
        JsonExample.createJSONMap4String();
        System.out.println();

        // 5.String→Class
        JsonExample.createJSONClass4String();
        System.out.println();

        // 6.String→List
        JsonExample.createJSONList4Strig();
        System.out.println();
    }
}
