package com.mhy.demo02.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mhy.demo02.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectUserById(@Param("id") Long id);

    void updateBalanceById(@Param("amount") int account,@Param("ew") UpdateWrapper<User> updateWrapper);

    int putUserById(@Param(Constants.WRAPPER) LambdaUpdateWrapper<User> userLambdaUpdateWrapper, @Param("money") Integer money);
}
