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
import java.util.Objects;

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

        UserShoppingAddress existingAddress = baseMapper.selectOne(new LambdaQueryWrapper<UserShoppingAddress>()
                .eq(UserShoppingAddress::getUserId, addressVO.getUserId())
                .eq(UserShoppingAddress::getReceiver, addressVO.getReceiver())
                .eq(UserShoppingAddress::getContact, addressVO.getContact())
                .eq(UserShoppingAddress::getCityCode, addressVO.getCityCode())
                .eq(UserShoppingAddress::getCountyCode, addressVO.getCountyCode())
                .eq(UserShoppingAddress::getProvinceCode, addressVO.getProvinceCode())
                .eq(UserShoppingAddress::getAddress, addressVO.getAddress()));
        if (existingAddress != null) {
            throw new ServerException("该地址已存在，请勿重复添加");
        }
        // 先进行一次查询，获取当前的默认地址列表
        List<UserShoppingAddress> defaultAddresses = baseMapper.selectList(new LambdaQueryWrapper<UserShoppingAddress>()
                .eq(UserShoppingAddress::getIsDefault, AddressDefaultEnum.DEFAULT_ADDRESS.getValue())
                .eq(UserShoppingAddress::getUserId, addressVO.getUserId()));

        if (Objects.equals(convert.getIsDefault(), AddressDefaultEnum.DEFAULT_ADDRESS.getValue())) {
            // 检查该地址是否已经在默认地址列表中
            if (defaultAddresses.stream().anyMatch(address -> address.getId().equals(addressVO.getId()))) {
                throw new ServerException("该地址已经是默认地址,请勿重复修改");
            }
            // 如果存在其他默认地址，则抛出异常
            if (!defaultAddresses.isEmpty()) {
                throw new ServerException("已存在默认地址,无法将该地址设置为默认地址");
            }
        } else {
            // 如果不存在默认地址，则将 convert 设置为默认地址
            if (defaultAddresses.isEmpty()) {
                convert.setIsDefault(AddressDefaultEnum.DEFAULT_ADDRESS.getValue());
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
        if (Objects.equals(addressVO.getIsDefault(), AddressDefaultEnum.DEFAULT_ADDRESS.getValue())) {
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

    @Override
    public void deleteAddress(Integer id) {
        //逻辑删除,将地址的delete_flag置为1即可
        UserShoppingAddress address = baseMapper.selectById(id);
        if (address == null){
            throw new ServerException("地址不存在");
        } else if (Objects.equals(address.getIsDefault(), AddressDefaultEnum.DEFAULT_ADDRESS.getValue())) {
            throw new ServerException("默认地址不能删除");
        }else {
            baseMapper.deleteById(id);
        }
    }
}
