package com.mhy.demo02.service.impl;

import com.mhy.demo02.domain.Address;
import com.mhy.demo02.mapper.AddressMapper;
import com.mhy.demo02.service.IAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孟环宇
 * @since 2024-05-05
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
