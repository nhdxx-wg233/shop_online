package com.soft2242.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2242.shop.common.exception.ServerException;
import com.soft2242.shop.convert.AddressConvert;
import com.soft2242.shop.entity.UserShoppingAddress;
import com.soft2242.shop.enums.AddressDefaultEnum;
import com.soft2242.shop.mapper.UserShoppingAddressMapper;
import com.soft2242.shop.service.UserShoppingAddressService;
import com.soft2242.shop.vo.AddressVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
@Service
public class UserShoppingAddressServiceImpl extends ServiceImpl<UserShoppingAddressMapper, UserShoppingAddress> implements UserShoppingAddressService {

    @Override
    public Integer saveShoppingAddress(AddressVO addressVO) {
        UserShoppingAddress convert = AddressConvert.INSTANCE.convert(addressVO);
        if (convert.getIsDefault() == AddressDefaultEnum.DEFAULT_ADDRESS.getValue()){
            List<UserShoppingAddress> list = baseMapper.selectList(new LambdaQueryWrapper<UserShoppingAddress>().eq(UserShoppingAddress::getIsDefault, AddressDefaultEnum.DEFAULT_ADDRESS.getValue()));
            if (list.size() > 0){
             throw new ServerException("已经存在默认地址,请勿重复操作");
            }
        }
        save(convert);
        return convert.getId();
    }

    @Override
    public Integer editShoppingAddress(AddressVO addressVO) {
        UserShoppingAddress userShoppingAddress = baseMapper.selectById(addressVO.getId());
        if (userShoppingAddress == null){
            throw new ServerException("地址不存在");
        }
        if (addressVO.getIsDefault() == AddressDefaultEnum.DEFAULT_ADDRESS.getValue()){
            UserShoppingAddress address = baseMapper.selectOne(new LambdaQueryWrapper<UserShoppingAddress>().eq(UserShoppingAddress::getUserId, addressVO.getUserId()).eq(UserShoppingAddress::getIsDefault, AddressDefaultEnum.DEFAULT_ADDRESS.getValue()));
            if (address != null){
                address.setIsDefault(AddressDefaultEnum.NOT_DEFAULT_ADDRESS.getValue());
                updateById(address);
            }
        }
        UserShoppingAddress address = AddressConvert.INSTANCE.convert(addressVO);
        updateById(address);
        return address.getId();
    }

    @Override
    public List<AddressVO> getAddressList(Integer userId) {
        List<UserShoppingAddress> list = baseMapper.selectList(new LambdaQueryWrapper<UserShoppingAddress>().eq(UserShoppingAddress::getUserId, userId));
        return AddressConvert.INSTANCE.convertToAddressVOList(list);
    }

    @Override
    public AddressVO getAddress(Integer id) {
        UserShoppingAddress address = baseMapper.selectOne(new LambdaQueryWrapper<UserShoppingAddress>().eq(UserShoppingAddress::getId, id));
        return AddressConvert.INSTANCE.convertToAddressVO(address);
    }
}
