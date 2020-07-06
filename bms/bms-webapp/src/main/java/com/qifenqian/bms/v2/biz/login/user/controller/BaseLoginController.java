package com.qifenqian.bms.v2.biz.login.user.controller;

import com.qifenqian.bms.platform.common.utils.CipherUtils;
import com.qifenqian.bms.platform.web.admin.login.service.LoginService;
import com.qifenqian.bms.platform.web.admin.role.service.RoleService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.web.admin.user.type.UserStatus;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.LoginAO;
import com.qifenqian.bms.v2.biz.system.user.bean.vo.UserVO;
import com.qifenqian.bms.v2.biz.system.user.service.BaseLoginService;
import com.qifenqian.bms.v2.common.constant.CacheConstants;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 登录接口
 * @date 2020/3/30 14:36
 */
@RestController
@Api(tags = "登录管理")
public class BaseLoginController extends BaseController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private BaseLoginService baseLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口")
    public UserVO login(@RequestBody LoginAO login) {
        if (StringUtils.isBlank(login.getAccount())) {
            throw new BizException("账号信息不能为空");
        }
        if (StringUtils.isBlank(login.getPassword())) {
            throw new BizException("密码信息不能为空");
        }
        // 忽略大小写
        String userCode = login.getAccount().toUpperCase();
        // 查询用户
        User loginUser = userService.selectUserSingleByCode(userCode);
        if (null == loginUser) {
            throw new BizException("员工号[" + userCode + "]不存在");
        }
        // 先校验密码，防止 别人根据提示套信息
        if (!loginUser.getPassword().equals(CipherUtils.encryptPassword(userCode, login.getPassword()))) {
            throw new BizException("密码错误");
        }
        if (null == loginUser.getStatus()) {
            throw new BizException("用户[" + userCode + "]状态异常");
        }
        if (UserStatus.FREEZE == loginUser.getStatus()) {
            throw new BizException("用户[" + userCode + "]已冻结");
        }
        if (UserStatus.LEAVE == loginUser.getStatus()) {
            throw new BizException("用户[" + userCode + "]已离职");
        }
        // 更改用户状态
        userService.updateStatusById(loginUser.getUserId(), UserStatus.LOGIN);
        // 角色信息
        loginUser.setRoleList(roleService.selectRoleListByUserId(loginUser.getUserId(), true));
        // 菜单信息
        loginUser.setMenuList(this.loginService.selectMenuTreeByUserId(loginUser.getUserId()));
        // 功能信息
        loginUser.setFunctionList(this.loginService.selectFunctionListByUserId(loginUser.getUserId()));
        // 注册用户信息
        WebUtils.registerUserInfo(loginUser);
        UserVO userVo = new UserVO();
        userVo.setId(Long.valueOf(loginUser.getUserId()));
        redisTemplateUtil.setCacheObject(CacheConstants.CURRENT_USER_ID_TEMP + loginUser.getUserId(), loginUser, CacheConstants.CURRENT_USER_TOKEN_EXP);
        /**
         * DO 复制部分用户信息
         */
        BeanUtils.copyProperties(loginUser, userVo);
        baseLoginService.supplementInfo(userVo);
        return userVo;
    }
}
