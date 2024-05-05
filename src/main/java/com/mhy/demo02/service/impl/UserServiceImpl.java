package com.mhy.demo02.service.impl;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.mhy.demo02.domain.User;
import com.mhy.demo02.mapper.UserMapper;
import com.mhy.demo02.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

    @Override
    public List<User> selectUserListAndAddressByIds(List<Long> ids) {
//        List<User> userList = listByIds(ids);

        List<User> list = Db.lambdaQuery(User.class).like(User::getUsername, "孟环宇").list();

        return list;

    }

    private final UserMapper userMapper;

//    public UserServiceImpl(UserMapper userMapper){
//        this.userMapper = userMapper;
//    }

    @Override
    public int putUserById(Long id, Integer money) {
        User user = userMapper.selectUserById(id);
        if (user != null){
            Integer balance = user.getBalance();
            if (balance-money >= 0){
                //余额充足的情况下 才会扣减余额
                LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<User>();

                userLambdaUpdateWrapper.eq(User::getId,id);

                //自定义mapper 修改金额方法
                int num =  userMapper.putUserById(userLambdaUpdateWrapper,money);


                return num;
            }
        }



        return  0;
    }

    @Override
    public User selectUserAndAddressByIds(Long id) throws Exception {
        //查询用户
        User user = getById(id);
        if (user == null){
            throw new Exception("用户不存在");
        }
        //查询地址
//        User user1 = Db.lambdaQuery(User.class).eq(User::getUsername, "孟环宇").one();
//        System.out.println("附加: "+user1);

        return user;
    }
}
