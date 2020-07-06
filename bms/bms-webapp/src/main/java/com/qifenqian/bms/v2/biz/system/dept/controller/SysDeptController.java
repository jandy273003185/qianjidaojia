package com.qifenqian.bms.v2.biz.system.dept.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.platform.web.admin.dept.bean.Dept;
import com.qifenqian.bms.platform.web.admin.dept.service.DeptService;
import com.qifenqian.bms.platform.web.admin.dept.type.DeptStatus;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.biz.system.dept.service.SysDeptService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName：SysDeptController
 * Description：DO 部门管理
 * Author: LiBin
 * Date：2019/12/28 3:33 下午
 */
@RestController
@Api(tags = "部门管理")
public class SysDeptController extends BaseController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping(value = "/sys/dept/list")
    public PageInfo<Dept> list(PageQuery pageQuery, @RequestBody Dept dept) {
        return sysDeptService.findDeptByPage(dept);
    }

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @PostMapping(value = "/sys/dept/save")
    public ResultData save(CurrentAccount currentAccount, @RequestBody Dept dept) {
        if (StringUtils.isBlank(dept.getDeptCode())) {
            throw new BizException("部门代码不能为空！");
        }
        boolean isExist = deptService.deptCodeIsExists(dept.getDeptCode());
        if (isExist) {
            throw new BizException("部门代码已存在");
        }
        try {
//            User user = WebUtils.getUserInfo();
            dept.setInstUser(currentAccount.getLoginId().intValue());
            dept.setStatus(DeptStatus.VALID);
            /**
             * 新增
             */
            deptService.addDept(dept);
        } catch (Exception e) {
            return ResultData.error(e.getMessage());
        }
        return ResultData.success();
    }

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @PostMapping(value = "/sys/dept/update")
    public ResultData update(CurrentAccount currentAccount, @RequestBody Dept dept) {
        if (dept.getDeptId() < 1) {
            throw new BizException("部门ID不能为空！");
        }
        if (StringUtils.isBlank(dept.getDeptCode())) {
            throw new BizException("部门代码不能为空！");
        }
        boolean isExist = deptService.deptCodeAndIdIsExists(dept);
        if (isExist) {
            throw new BizException("部门代码已被使用");
        }
        try {
//            User user = WebUtils.getUserInfo();
            dept.setLupdUser(currentAccount.getLoginId().intValue());
            /**
             * 更新
             */
            deptService.editDept(dept);
        } catch (Exception e) {
            return ResultData.error(e.getMessage());
        }
        return ResultData.success();
    }


    @PostMapping(value = "/sys/dept/delete")
    public ResultData delete(CurrentAccount currentAccount,@RequestBody Dept dept) {
        if (dept.getDeptId() < 1) {
            throw new BizException("部门ID不能为空！");
        }
        try {
//            User user = WebUtils.getUserInfo();
            dept.setLupdUser(currentAccount.getLoginId().intValue());
            dept.setStatus(DeptStatus.DISABLE);
            /**
             * 删除部门
             */
            deptService.deleteDept(dept);
        } catch (Exception e) {
            return ResultData.error(e.getMessage());
        }
        return ResultData.success();
    }

}
