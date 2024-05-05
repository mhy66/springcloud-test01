package com.mhy.demo02;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.DB2Dialect;
import com.mhy.demo02.domain.User;
import com.mhy.demo02.domain.json.UserInfo;
import com.mhy.demo02.mapper.UserMapper;
import com.mhy.demo02.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
class Demo02ApplicationTests{

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
//        User user = userMapper.selectUserById(1L);

//        System.out.println("用户："+user);

        User insertUser = new User();
        insertUser.setUsername("孟环宇2");
        insertUser.setPassword("54321");
        insertUser.setInfo(new UserInfo("小小孟","1223456",30001));


        userMapper.insert(insertUser);
    }

    @Test
    void queryWrapperTest(){
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda()
                .select(User::getUsername,User::getPassword)
                .eq(User::getId,"1");

        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);
    }

    @Test
    void updateWrapperTest(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
//                .setSql("balance = balance - 200")
//                .eq("id",1L);

        updateWrapper.set("username","lose").eq("id",1L);

        userMapper.update(null,updateWrapper);
    }

//wrapper 自定义SQL
    @Test
    void customUpdateSqlTest(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();

        updateWrapper.eq("id",1L);

        userMapper.updateBalanceById(200,updateWrapper);
    }

//    IService 基本方法使用
    @Autowired
    private UserService userService;
    @Test
    void userServiceSql(){
        List<User> list = userService.list();
        list.forEach(System.out::println);

        User user = userService.getById(1L);

        System.out.println("单独对象: "+user);
    }


//    批量新增 与 普通for循环新增效率对比
    @Test
    void batchInsertData(){
        List<User> userList = getUserList();

        long startTIme = System.currentTimeMillis();
        //普通新增数据
//        for (int i = 0; i < userList.size(); i++) {
//            userService.save(userList.get(i));
//        }

        for (int i = 0; i < userList.size(); i++) {
            List<User> batchList = new ArrayList<>();
            batchList.add(userList.get(i));
            if (i%1000 == 0){
                userService.saveBatch(batchList);
                batchList.clear();
            }
        }

        long endTIme = System.currentTimeMillis();

        System.out.println("耗时："+ (endTIme - startTIme));


    }

    public List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setUsername("名字"+i);
            user.setPassword("abcd"+1);
            user.setBalance(1000);

            userList.add(user);
        }



        return userList;

    }


    @Test
    void test01(){
        List<User> userList = new ArrayList<>();

        User user = new User();
        user.setId(1L);
        user.setUsername("111");
        userList.add(user);

        user = new User();
        user.setId(2L);
        user.setUsername("222");
        userList.add(user);

        List<String> collect = userList.stream().map(t -> t.getUsername()).collect(Collectors.toList());

        System.out.println(collect);
    }

}
