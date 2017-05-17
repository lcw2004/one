package com.lcw.one.user.rest;

import com.lcw.one.notify.service.NotifyService;
import com.lcw.one.user.bean.vo.SupplierUserRegistryVO;
import com.lcw.one.user.constant.UserSupplierStatusEnum;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.user.entity.UserSupplierEO;
import com.lcw.one.user.service.UserInfoEOService;
import com.lcw.one.user.service.UserSupplierEOService;
import com.lcw.one.util.http.ResponseMessage;
import com.lcw.one.util.http.Result;
import com.lcw.one.util.utils.RandomUtils;
import com.lcw.one.util.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/${restPath}/user/supplierRegistry")
@Api(description = "用户 - 供应商注册")
public class UserSupplierEORegistryRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserSupplierEORegistryRestController.class);
    private static final String REGISTRY_VALID_CODE = "registryValidCode";

    @Autowired
    private UserSupplierEOService userSupplierEOService;

    @Autowired
    private UserInfoEOService userInfoEOService;

    @Autowired
    private NotifyService notifyService;

    @ApiOperation(value = "验证供应商是否注册过")
    @GetMapping("/validName")
    public ResponseMessage validName(@NotNull @RequestParam String name) {
        UserSupplierEO userSupplierEO = userSupplierEOService.getUserSupplierEOByName(name);
        if (userSupplierEO != null) {
            return Result.error("0001", "该机构已经注册过了");
        }

        return Result.success();
    }

    @ApiOperation(value = "验证账号是否注册过")
    @GetMapping("/validAccount")
    public ResponseMessage validAccount(@NotNull @RequestParam String account) {
        UserInfoEO userInfoEO = userInfoEOService.getUserByLoginName(account);
        if (userInfoEO != null) {
            return Result.error("0002", "该账户已经注册过了");
        }

        return Result.success();
    }

    @ApiOperation(value = "发送验证码")
    @GetMapping("/sendValidCode")
    public ResponseMessage sendValidCode(HttpServletRequest request, @NotNull @Email(message = "请输入正确的验证") @RequestParam String email) {
        String validCode = RandomUtils.randomNumberString(6);
        request.getSession().setAttribute(REGISTRY_VALID_CODE, validCode);
        logger.info("ValidCode:" + validCode);
        notifyService.sendEmail(email, "您的验证码是：" + validCode);
        return Result.success();
    }

    @ApiOperation(value = "注册登录账号")
    @PostMapping("/registryUserInfo")
    public ResponseMessage<String> registryUserInfo(HttpServletRequest request, @RequestBody SupplierUserRegistryVO registry) {
        try {
            // 验证
            if (StringUtils.isEmpty(registry.getValidCode())) {
                return Result.error("0003", "请输入验证码");
            }
            String validCode = (String) request.getSession().getAttribute(REGISTRY_VALID_CODE);
            if (!registry.getValidCode().equals(validCode)) {
                return Result.error("0003", "验证码验证不通过");
            }

            ResponseMessage validResult = validAccount(registry.getAccount());
            if (!validResult.isOk()) {
                return validResult;
            }
            validResult = validName(registry.getSupplierName());
            if (!validResult.isOk()) {
                return validResult;
            }

            // 保存
            UserSupplierEO userSupplierEO = userSupplierEOService.registrySupplierUserInfo(registry, RequestUtils.getClientIp(request));
            return Result.success(userSupplierEO.getSupplierId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation(value = "获取供应商信息")
    @GetMapping("/getUserSupplierInfo")
    public ResponseMessage<UserSupplierEO> getUserSupplierInfo(HttpServletRequest request, String id) {
        UserSupplierEO userSupplierEO = userSupplierEOService.get(id);
        return Result.success(userSupplierEO);
    }

    @ApiOperation(value = "完善供应商信息")
    @PostMapping("/updateUserSupplierEO")
    public ResponseMessage updateUserSupplierEO(@RequestBody UserSupplierEO registry) {
        try {
            userSupplierEOService.prefectSupplier(registry);
            return Result.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
}
