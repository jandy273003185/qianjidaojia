package com.qifenqian.bms.v2.biz.system.user.bean.vo;

import com.qifenqian.bms.v2.biz.system.function.bean.vo.FunctionTreeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/3/30 15:42
 */
@ApiModel(value = "用户信息")
public class UserVO {
    @ApiModelProperty(value = "ID")
    private Long id;
    private LocalDateTime lastLoginTime;
    @ApiModelProperty(value = "员工号")
    private String userCode;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "部门编号")
    private Integer deptId;
    @ApiModelProperty(value = "部门编号")
    private String deptName;
    @ApiModelProperty(value = "TOKEN")
    private String token;
    @ApiModelProperty(value = "菜单列表")
    private List<FunctionTreeVO> functionTreeVOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<FunctionTreeVO> getFunctionTreeVOS() {
        return functionTreeVOS;
    }

    public void setFunctionTreeVOS(List<FunctionTreeVO> functionTreeVOS) {
        this.functionTreeVOS = functionTreeVOS;
    }
}
