package com.qifenqian.bms.v2.biz.system.role.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.platform.web.admin.function.service.FunctionService;
import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.role.service.RoleService;
import com.qifenqian.bms.platform.web.admin.rolefunction.bean.RoleFunction;
import com.qifenqian.bms.platform.web.admin.rolefunction.service.RoleFunctionService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.v2.biz.system.function.bean.vo.FunctionTreeVO;
import com.qifenqian.bms.v2.biz.system.function.service.SysFunctionService;
import com.qifenqian.bms.v2.biz.system.role.bean.domain.SysRoleAO;
import com.qifenqian.bms.v2.biz.system.role.service.SysRoleFunctionService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName：SysRoleController
 * Description：TODO
 * Author: LiBin
 * Date：2019/12/27 4:19 下午
 */
@RestController
@Api(tags = "角色管理")
public class SysRoleController extends BaseController {
    @Autowired
    private FunctionService functionService;
    @Autowired
    private SysRoleFunctionService sysRoleFunctionService;
    @Autowired
    private RoleFunctionService roleFunctionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SysFunctionService sysFunctionService;

    /**
     * 角色已有的菜单
     *
     * @param sysRoleDO
     * @return
     */
    @PostMapping(value = "/sys/role/function")
    public List<RoleFunction> selectFunction(@RequestBody SysRoleAO sysRoleDO) {
        int roleId = sysRoleDO.getRoleId();
        if (roleId < 1) {
            throw new BizException("角色ID不能为空!");
        }
        return roleFunctionService.selectList(roleId);
    }

    /**
     * 角色菜单权限树
     *
     * @param sysRoleDO
     * @return
     */
    @PostMapping(value = "/sys/role/findRoleFunctionTree")
    public List<FunctionTreeVO> findRoleFunctionTree(@RequestBody SysRoleAO sysRoleDO) {
        int roleId = sysRoleDO.getRoleId();
        if (roleId < 1) {
            return sysFunctionService.findFunctionTree();
        }
        return sysRoleFunctionService.findRoleFunctionTree(roleId);
    }

    /**
     * 角色已有的菜单
     *
     * @param sysRoleDO
     * @return
     */
    @PostMapping(value = "/sys/role/list")
    public PageInfo<Role> selectFunction(PageQuery pageQuery, @RequestBody Role role) {
        return sysRoleFunctionService.findRoleByPage(role);
    }

    /**
     * 角色删除
     *
     * @param sysRoleDO
     * @return
     */
    @PostMapping(value = "/sys/role/delete")
    public ResultData delete(CurrentAccount currentAccount, @RequestBody SysRoleAO sysRoleDO) {
        int roleId = sysRoleDO.getRoleId();
        if (roleId < 1) {
            throw new BizException("roleId不能为空!");
        }
        sysRoleDO.setLupdUser(currentAccount.getLoginId().intValue());
        sysRoleFunctionService.deleteRoleByRoleId(sysRoleDO);
        return ResultData.success();
    }

    /**
     * 角色新增
     *
     * @param sysRoleDO
     * @return
     */
    @PostMapping(value = "/sys/role/save")
    public ResultData save(CurrentAccount currentAccount, @RequestBody SysRoleAO sysRoleDO) {
        String checkValue = sysRoleDO.getCheckValue();
        Role requestRole = new Role();
        BeanUtils.copyProperties(sysRoleDO, requestRole);
        if (StringUtils.isBlank(requestRole.getRoleCode())) {
            throw new BizException("角色代码为空");
        }
        if (StringUtils.isEmpty(requestRole.getRoleName())) {
            throw new BizException("角色名称为空");
        }
        boolean isExist = roleService.roleCodeIsExists(requestRole.getRoleCode());
        if (isExist) {
            throw new BizException("角色代码已存在");
        }
        // 保存新增
        requestRole.setInstUser(currentAccount.getLoginId().intValue());
        roleService.saveAddRole(requestRole, checkValue);
        return ResultData.success();
    }


    /**
     * 角色修改
     *
     * @param sysRoleDO
     * @return
     */
    @PostMapping(value = "/sys/role/update")
    public ResultData edit(CurrentAccount currentAccount, @RequestBody SysRoleAO sysRoleDO) {
        String checkValue = sysRoleDO.getCheckValue();
        Role requestRole = new Role();
        BeanUtils.copyProperties(sysRoleDO, requestRole);
        int roleId = requestRole.getRoleId();
        if (roleId < 1) {
            throw new BizException("角色ID不能为空!");
        }
        boolean isExist = roleService.roleCodeAndIdIsExists(requestRole);
        if (isExist) {
            throw new BizException("角色代码已被使用");
        }
        User user = WebUtils.getUserInfo();
        requestRole.setLupdUser(user.getUserId());
        // 菜单删除
        roleFunctionService.deleteRoleFunction(roleId);
        // 增加菜单
        if (!StringUtils.isEmpty(checkValue)) {
            List<String> list = functionService.checkedFunction(checkValue);
            if (CollectionUtils.isEmpty(list)) {
                throw new BizException("角色菜单集合为空");
            }
            List<RoleFunction> roleFunctions = roleFunctionService.roleFunctionList(requestRole.getRoleId(), list,
                    user.getUserId());
            roleFunctionService.insertList(roleFunctions);
        }
        // 保存修改
        requestRole.setLupdUser(currentAccount.getLoginId().intValue());
        roleService.updateRoleById(requestRole);
        return ResultData.success();
    }
}
