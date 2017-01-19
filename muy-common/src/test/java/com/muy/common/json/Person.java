package com.muy.common.json;

/**
 * Created by yanglikai on 2017/1/17.
 */
public class Person {
    private String userId;
    private String userName;

    @Override
    public String toString() {
        return "Person{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
