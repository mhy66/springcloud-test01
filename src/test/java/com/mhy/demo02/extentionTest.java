package com.mhy.demo02;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.mhy.demo02.domain.User;
import com.mhy.demo02.domain.vo.UserVO;
import com.mhy.demo02.service.UserService;
import com.mhy.demo02.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class extentionTest {

    @Autowired
    private UserService userService;

    @Test
    void queryPages(){

        Page<User> userPage = Page.of(2,2);

//        userPage.addOrder(new OrderItem())


        //分页查询
        Page<User> page = userService.lambdaQuery().like(User::getUsername,"孟环宇").page(userPage);

        List<User> userList = page.getRecords();
        System.out.println(userList);


        Page<UserVO> voPage = new Page<UserVO>();
        voPage.setTotal(page.getTotal());
        voPage.setCurrent(page.getCurrent());
        voPage.setSize(page.getSize());
        voPage.setRecords(BeanUtil.copyToList(userList, UserVO.class));

        System.out.println(voPage);


    }


}
