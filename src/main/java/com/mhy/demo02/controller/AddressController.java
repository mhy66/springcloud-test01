package com.mhy.demo02.controller;


import com.mhy.demo02.domain.Address;
import com.mhy.demo02.domain.User;
import com.mhy.demo02.service.IAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 孟环宇
 * @since 2024-05-05
 */
@Api(tags = "用户地址接口")
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService addressService;

    //逻辑删除
    @ApiOperation(value = "逻辑删除地址",notes = "根据id逻辑删除地址")
    @DeleteMapping("/logicDeleteAddressById/{id}")
    public Address logicDeleteAddressById(@PathVariable("id") Long id){

        boolean b = addressService.removeById(id);
        System.out.println(b);

        Address address = addressService.getById(id);
        System.out.println(address);

        return address;

    }

}
