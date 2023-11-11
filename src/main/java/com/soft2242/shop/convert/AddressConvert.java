package com.soft2242.shop.convert;

import com.soft2242.shop.entity.UserShoppingAddress;
import com.soft2242.shop.vo.AddressVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressConvert {
    AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);


    UserShoppingAddress convert(AddressVO addressVO);


    List<AddressVO> convertToAddressVOList(List<UserShoppingAddress> addressList);


    AddressVO convertToAddressVO(UserShoppingAddress userShoppingAddress);
}