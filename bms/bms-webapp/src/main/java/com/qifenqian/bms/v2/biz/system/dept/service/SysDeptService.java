package com.qifenqian.bms.v2.biz.system.dept.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.platform.web.admin.dept.bean.Dept;
import com.qifenqian.bms.platform.web.admin.dept.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 部门service
 * @date 2020/4/2 09:47
 */
@Service
public class SysDeptService {

    @Autowired
    private DeptMapper deptMapper;

    public PageInfo<Dept> findDeptByPage(Dept dept) {
        List<Dept> depts = deptMapper.selectDeptList(dept);
        return new PageInfo(depts);
    }
}
