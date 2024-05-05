package com.mhy.demo02.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.mhy.demo02.domain.User;
import com.mhy.demo02.domain.dto.UserFormDTO;
import com.mhy.demo02.domain.vo.UserVO;
import com.mhy.demo02.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api(tags = "用户管理接口")
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @ApiOperation(value = "添加用户信息", notes = "根据前端传过来的表单 来添加用户信息")
    @PostMapping("/addUser")
    @ResponseBody
    public void addUser(@RequestBody UserFormDTO userFormDTO){
        //将表单实体类转换成 user
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        userService.save(user);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户 ID 获取用户信息")
    @GetMapping("/queryUserById/{id}")
    @ResponseBody
    public UserVO queryUserById(@PathVariable("id") Long id) throws Exception {
        User user = userService.selectUserAndAddressByIds(id);

        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户 ID 删除用户信息")
    @DeleteMapping("/deleteUserById/{id}")
    @ResponseBody
    public void deleteUserById(@PathVariable("id") Long id){
        userService.removeById(id);
    }

    @ApiOperation(value = "批量查询用户信息", notes = "根据用户 IDs集合 批量查询用户信息")
    @GetMapping("/queryUserListByIds")
    @ResponseBody
    public List<UserVO> queryUserListByIds(@RequestParam("ids") List<Long> ids){
//        List<User> userList = userService.listByIds(ids);
//
//        return BeanUtil.copyToList(userList, UserVO.class);
        List<User> userList = userService.selectUserListAndAddressByIds(ids);

        return BeanUtil.copyToList(userList, UserVO.class);
    }


    @ApiOperation(value = "修改金额", notes = "根据用户 ID 修改用户金额")
    @PutMapping("/putUserBalanceById/{id}/deduction/{money}")
    @ResponseBody
    public void putUserById(@ApiParam("用户id") @PathVariable("id") Long id,
                            @ApiParam("扣减金额") @PathVariable("money") Integer money){

       int num = userService.putUserById(id,money);

    }


}
