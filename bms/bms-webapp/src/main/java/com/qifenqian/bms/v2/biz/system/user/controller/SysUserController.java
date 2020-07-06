package com.qifenqian.bms.v2.biz.system.user.controller;


import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.web.admin.user.type.UserStatus;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.biz.system.user.service.SysUserService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户管理")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/sys/user/list")
    public PageInfo<User> list(PageQuery pageQuery, @RequestBody User user) {
        return sysUserService.findUserByPage(user);
    }

    /**
     * 删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "sys/user/delete", method = RequestMethod.POST)
    public ResultData delete(@RequestBody User user) {
        int userId = user.getUserId();
        if (userId < 1) {
            throw new BizException("userId不能为空!");
        }
        userService.deleteUserById(userId);
        return ResultData.success();
    }


    /**
     * 新增
     *
     * @param
     * @return
     */
    @PostMapping(value = "sys/user/add")
    public ResultData add(CurrentAccount currentAccount, @RequestBody User user) {
        boolean userNameIsExists = userService.userNameIsExists(user.getUserName());
        if (StringUtils.isBlank(user.getRole())) {
            throw new BizException("角色为空");
        }
        if (userNameIsExists) {
            throw new BizException("用户名已存在");
        }
        user.setStatus(UserStatus.VALID);
        user.setInstUser(currentAccount.getLoginId().intValue());
        userService.saveAddUser(user, user.getRole());

        return ResultData.success();
    }

    /**
     * 修改
     *
     * @param
     * @return
     */
    @PostMapping(value = "sys/user/update")
    public ResultData update(CurrentAccount currentAccount, @RequestBody User user) {
        int userId = user.getUserId();
        if (userId < 1) {
            throw new BizException("userId不能为空!");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw new BizException("密码为空");
        }
        boolean userNameIsExists = userService.userNameAndIdIsExists(user);
        if (userNameIsExists) {
            throw new BizException("用户名已存在");
        }
        String userPwd = userService.getUserPwd(user.getUserId());
        if (user.getPassword().equals(userPwd)) {
            //throw new BizException("密码相同时不做操作");
            user.setPassword("");
        }
        user.setLupdUser(currentAccount.getLoginId().intValue());
        userService.updateUserById(user, user.getRole());
        return ResultData.success();
    }
}
