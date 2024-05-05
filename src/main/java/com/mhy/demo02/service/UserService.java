package com.mhy.demo02.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mhy.demo02.domain.User;

import java.util.List;

public interface UserService extends IService<User> {

    /** 根据id 去修改余额*/
    int putUserById(Long id, Integer money);

    User selectUserAndAddressByIds(Long id) throws Exception;

    List<User> selectUserListAndAddressByIds(List<Long> ids);
}
