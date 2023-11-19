package com.soft2242.shop.controller;

import com.soft2242.shop.common.exception.ServerException;
import com.soft2242.shop.common.result.Result;
import com.soft2242.shop.service.UserShoppingAddressService;
import com.soft2242.shop.vo.AddressVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.soft2242.shop.common.utils.ObtainUserIdUtils.getUserId;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
@Tag(name = "地址管理")
@RestController
@RequestMapping("member")
@AllArgsConstructor
public class UserShoppingAddressController {
    private final UserShoppingAddressService userShoppingAddressService;

    @Operation(summary = "添加收货地址")
    @PostMapping("address")
    public Result<Integer> saveAddress(@RequestBody @Validated AddressVO addressVO, HttpServletRequest request) {
        Integer userId = getUserId(request);
        addressVO.setUserId(userId);
        Integer addressId = userShoppingAddressService.saveShoppingAddress(addressVO);
        return Result.ok(addressId);
    }

    @Operation(summary = "修改收货地址")
    @PutMapping("address")
    public Result<Integer> editAddress(@RequestBody @Validated AddressVO addressVO, HttpServletRequest request) {
        if (addressVO.getId() == null) {
            throw new ServerException("请求参数不能为空");
        }
        addressVO.setUserId(getUserId(request));
        Integer addressId = userShoppingAddressService.editShoppingAddress(addressVO);
        return Result.ok(addressId);
    }

    @Operation(summary = "收货地址列表")
    @GetMapping("address")
    public Result<List<AddressVO>> getAddressList(HttpServletRequest request) {
        Integer userId = getUserId(request);
        List<AddressVO> addressList = userShoppingAddressService.getAddressList(userId);
        return Result.ok(addressList);
    }

    @Operation(summary = "收货地址详情")
    @GetMapping("address/detail")
    public Result<AddressVO> getAddress(@RequestParam Integer id) {
        AddressVO address = userShoppingAddressService.getAddress(id);
        return Result.ok(address);
    }

    @Operation(summary = "删除收货地址")
    @DeleteMapping("address")
    public Result deleteAddress(@RequestParam Integer id) {
        userShoppingAddressService.deleteAddress(id);
        return Result.ok();
    }
}
