package com.qifenqian.bms.v2.biz.system.function.controller;


import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.function.service.FunctionService;
import com.qifenqian.bms.platform.web.admin.rolefunction.service.RoleFunctionService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.biz.system.function.service.SysFunctionService;
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

@RestController
@Api(tags = "权限管理")
public class SysFunctionController extends BaseController {

    @Autowired
    private FunctionService functionService;
    @Autowired
    private SysFunctionService sysFunctionService;
    @Autowired
    private RoleFunctionService roleFunctionService;

    /**
     * 查询菜单
     *
     * @param function
     * @return
     */
    @PostMapping("/sys/function/list")
    public PageInfo<Function> selectFunction(PageQuery pageQuery, @RequestBody Function function) {
        return sysFunctionService.findFunctionByPage(function);
    }


    /**
     * 新增菜单
     *
     * @param function
     * @return
     */
    @PostMapping(value = "/sys/function/add")
    public ResultData add(CurrentAccount currentAccount, @RequestBody Function function) {
        if (StringUtils.isBlank(function.getFunctionCode())) {
            throw new BizException("新增菜单编码不能为空");
        }
        boolean isExist = functionService.functionCodeIsExists(function.getFunctionCode());
        if (isExist) {
            throw new BizException("菜单编码已存在");
        }
//        User user = WebUtils.getUserInfo();
        function.setInstUser(currentAccount.getLoginId().intValue());
        functionService.insertFunction(function);
        return ResultData.success();
    }

    /**
     * 修改菜单
     *
     * @param function
     * @return
     */
    @PostMapping(value = "/sys/function/update")
    public ResultData update(CurrentAccount currentAccount, @RequestBody Function function) {

        if (function.getFunctionId() < 1) {
            throw new BizException("菜单功能编号不能为空");
        }
        if (StringUtils.isBlank(function.getFunctionCode())) {
            throw new BizException("菜单编码不能为空");
        }
        boolean isExist = functionService.functionCodeAndIdIsExists(function);
        if (isExist) {
            throw new BizException("菜单代码已存在");
        }
        function.setLupdUser(currentAccount.getLoginId().intValue());
        functionService.updateFunction(function);
        return ResultData.success();
    }

    /**
     * 删除菜单
     *
     * @param function
     * @return
     */
    @PostMapping(value = "/sys/function/delete")
    public ResultData delete(CurrentAccount currentAccount,@RequestBody Function function) {
//        User user = WebUtils.getUserInfo();
        function.setLupdUser(currentAccount.getLoginId().intValue());
        if (function.getFunctionId() < 1) {
            throw new BizException("菜单功能编号不能为空");
        }
        // 删除菜单
        functionService.deleteFunction(function.getFunctionId());
        // 删除角色与菜单的关系
        roleFunctionService.deleteRoleFunctionbyFunctionId(function.getFunctionId());
        return ResultData.success();
    }

}
