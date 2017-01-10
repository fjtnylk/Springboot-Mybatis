package com.muy.common.service.user.impl;

import com.muy.common.dto.user.UserDto;
import com.muy.common.entity.user.User;
import com.muy.common.repository.user.UserRepository;
import com.muy.common.service.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yanglikai on 2016/8/28.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserDto getByUserName(String userName) {
        User user = userRepository.queryByUserName(userName);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    @Override
    public void updateByUserId(String userId) {
        userRepository.updateByUserId(userId);
    }
}
