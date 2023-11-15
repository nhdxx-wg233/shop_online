package com.soft2242.shop.convert;

import com.soft2242.shop.entity.User;
import com.soft2242.shop.vo.LoginResultVO;
import com.soft2242.shop.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author ycshang
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mapping(dateFormat = "yyyy-MM-dd", target = "birthday")
    @Mapping(target = "provinceCode", expression = "java(entity.getProvinceCode().isEmpty()?null:entity.getProvinceCode())")
    @Mapping(target = "cityCode", expression = "java(entity.getCityCode().isEmpty()?null:entity.getCityCode())")
    @Mapping(target = "countyCode", expression = "java(entity.getCountyCode().isEmpty()?null:entity.getCountyCode())")
    @Mapping(target = "profession", expression = "java(entity.getProfession()==null?null:entity.getProfession())")
    User convert(UserVO entity);


    @Mapping(expression = "java(MapStruct.transferTime(user.getBirthday()))", target = "birthday")
    UserVO convertToUserVO(User user);


    LoginResultVO convertToLoginResultVO(User user);


    class MapStruct {
        public static Timestamp transferTime(LocalDateTime value) {
            return Timestamp.valueOf(value);
        }


        public static LocalDateTime transferTimeStamp(Timestamp time) {
            return time.toLocalDateTime();
        }
    }
}