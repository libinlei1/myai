package com.sky.service.impl;

import com.sky.domain.po.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.IAddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地址簿 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-04-17
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

}
