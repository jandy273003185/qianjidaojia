package com.qifenqian.bms.v2.biz.system.role.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.platform.web.admin.function.mapper.FunctionMapper;
import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.role.mapper.RoleMapper;
import com.qifenqian.bms.platform.web.admin.role.type.RoleIsValid;
import com.qifenqian.bms.v2.biz.system.role.bean.domain.SysRoleAO;
import com.qifenqian.bms.v2.biz.system.function.bean.vo.FunctionTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 角色功能
 * @date 2020/4/2 10:28
 */
@Service
public class SysRoleFunctionService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private FunctionMapper functionMapper;

    public PageInfo<Role> findRoleByPage(Role role) {
        List<Role> roles = roleMapper.selectRoleList(role);
        return new PageInfo<>(roles);
    }

    public List<FunctionTreeVO> findRoleFunctionTree(Integer roleId){
        List<FunctionTreeVO> roleFunctionTree = functionMapper.findRoleFunctionTree(roleId, null);
        return roleFunctionTree;
    }

    public void deleteRoleByRoleId(SysRoleAO sysRoleDO) {
        Role updateRole = new Role();
        updateRole.setRoleId(sysRoleDO.getRoleId());
        updateRole.setIsValid(RoleIsValid.N);
        roleMapper.updateRole(updateRole);
    }
}
