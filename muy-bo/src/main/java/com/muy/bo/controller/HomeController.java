package com.muy.bo.controller;

import com.muy.common.dto.user.UserDto;
import com.muy.common.service.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yanglikai on 2017/1/7.
 */
@RestController
@RequestMapping(value = "/bo/home")
public class HomeController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello";
    }

    @RequestMapping(value = "/getByUserName", method = RequestMethod.GET)
    @ResponseBody
    public UserDto getByUserName(String userName) {
        UserDto userDto = userService.getByUserName(userName);
        return userDto;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public String updateByUserId() {
        userService.updateByUserId("10001");
        return "更新成功";
    }
}
