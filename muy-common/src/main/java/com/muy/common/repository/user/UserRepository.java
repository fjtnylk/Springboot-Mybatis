package com.muy.common.repository.user;

import com.muy.common.datasource.annotation.DbMsEnum;
import com.muy.common.datasource.annotation.SwitchDs;
import com.muy.common.entity.user.User;
import com.muy.common.mapper.user.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * dao
 * Created by yanglikai on 2016/8/28.
 */
@Repository
public class UserRepository {
    @Resource
    private UserMapper userMapper;

    @SwitchDs(ms = DbMsEnum.Slave)
    public User queryByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @SwitchDs(ms = DbMsEnum.Master)
    public void updateByUserId(String userId) {
        userMapper.updateByUserId(userId);
    }
}
