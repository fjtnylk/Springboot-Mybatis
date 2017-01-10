package com.muy.common.mapper.user;

import com.muy.common.entity.user.User;
import com.muy.common.utils.MuyMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by yanglikai on 2016/8/17.
 */
public interface UserMapper extends MuyMapper<User> {
    User findByUserName(String userName);

    int updateByUserId(String userId);
}
