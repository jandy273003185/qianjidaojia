package com.qifenqian.bms.v2.biz.system.user.service;

import com.qifenqian.bms.platform.web.admin.dept.bean.Dept;
import com.qifenqian.bms.platform.web.admin.dept.mapper.DeptMapper;
import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.function.mapper.FunctionMapper;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.constant.CacheConstants;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.qifenqian.bms.v2.common.token.JWTUtil;
import com.qifenqian.bms.v2.biz.system.function.bean.vo.FunctionTreeVO;
import com.qifenqian.bms.v2.biz.system.user.bean.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LiBin
 * @Description: 登录service
 * @date 2020/3/31 10:14
 */
@Service
public class BaseLoginService extends BaseService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private FunctionMapper functionMapper;


    public void supplementInfo(UserVO userVo) {
        /**
         * DO 生成token信息返回
         */
        createToken(userVo);
        /**
         * DO 添加部门信息
         */
        setUserDeptInfo(userVo);
        /**
         * DO 查询个人相关的菜单信息,树结构的
         */
        setUserMenuTree(userVo);
        /**
         * 缓存当前登陆者信息
         */
        setCacheCurrentUser(userVo);
    }

    private void setCacheCurrentUser(UserVO userVo) {
        Long id = userVo.getId();
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(userVo, currentAccount);
        currentAccount.setLoginId(id);
        /**
         * DO 查询出个人权限信息 set结构
         */
        setUserResource(currentAccount);
        redisTemplateUtil.setCacheObject(CacheConstants.CURRENT_USER_ID + id, currentAccount, CacheConstants.CURRENT_USER_TOKEN_EXP * 4l);

    }

    /**
     * @param [userVo]
     * @return void
     * @description 添加部门信息
     * @author LiBin
     * @date 2020/3/31 11:39
     */
    private void setUserDeptInfo(UserVO userVo) {
        Dept deptCondition = new Dept();
        deptCondition.setDeptId(userVo.getDeptId());
        Dept dept = deptMapper.selectDeptSingle(deptCondition);
        if (null != dept) {
            userVo.setDeptName(dept.getDeptName());
        }
    }


    /**
     * @param [userVo]
     * @return void
     * @description 设置用户菜单丢进去
     * @author LiBin
     * @date 2020/3/30 15:57
     */
    private void setUserMenuTree(UserVO userVo) {
        List<FunctionTreeVO> functionTreeVOS = this.functionMapper.findMenuTreeByUserId(userVo.getId(), null);
        userVo.setFunctionTreeVOS(functionTreeVOS);
    }

    /**
     * @param [account]
     * @param userVo
     * @return void
     * @description 设置用户权限丢进去
     * @author LiBin
     * @date 2020/3/30 15:57
     */
    private void setUserResource(CurrentAccount currentAccount) {
        int id = currentAccount.getLoginId().intValue();
        List<Function> functions = this.functionMapper.selectFunctionListByUserId(id);
        Set<String> functionSet = new HashSet<>();
        for (Function function : functions) {
            functionSet.add(function.getFunctionUrl());
        }
        currentAccount.setFunctionSet(functionSet);
    }

    /***
     * @description 设置token丢进去
     * @param [userVo]
     * @return void
     * @author LiBin
     * @date 2020/3/30 16:01
     */
    private void createToken(UserVO userVo) {
        String id = userVo.getId().toString();
        Instant now = Instant.now();
        String secretKey = JWTUtil.genSecretKey(now);
        LocalDateTime lastLoginTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        //记录缓存用户信息
        userVo.setLastLoginTime(lastLoginTime);
        String token = JWTUtil.create(secretKey, userVo, now, CacheConstants.CURRENT_USER_TOKEN_EXP);
        userVo.setToken(token);
        //记录缓存刷新 token
        redisTemplateUtil.setCacheObject(CacheConstants.CURRENT_USER_TOKEN + id, token, CacheConstants.CURRENT_USER_TOKEN_EXP * 2l);
    }
}
