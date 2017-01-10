package com.muy.common.service.user;

import com.muy.common.dto.user.UserDto;

/**
 * Created by yanglikai on 2016/8/28.
 */
public interface UserService {
    UserDto getByUserName(String userName);

    void updateByUserId(String userId);
}
