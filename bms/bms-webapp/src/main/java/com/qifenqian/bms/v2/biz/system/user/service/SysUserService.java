package com.qifenqian.bms.v2.biz.system.user.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.mapper.UserMapper;
import com.qifenqian.bms.platform.web.admin.userRole.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 用户service
 * @date 2020/4/7 10:38
 */
@Service
public class SysUserService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserMapper userMapper;

    public PageInfo<User> findUserByPage(User user) {
        List<User> users = userMapper.selectUserList(user);
        for (User u : users) {
            List<Role> userRoles = userRoleMapper.selectUserRoleByUserId(u.getUserId());
            u.setRoleList(userRoles);
        }
        return new PageInfo<>(users);
    }
}
