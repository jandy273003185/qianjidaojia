package com.qifenqian.bms.v2.biz.system.function.bean.vo;

import com.qifenqian.bms.platform.web.admin.function.type.IsMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiBin
 * @Description: 菜单结构
 * @date 2020/3/31 10:09
 */
@ApiModel(value = "菜单树信息")
public class FunctionTreeVO implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private int userId;
    @ApiModelProperty(value = "功能编号")
    private int functionId;
    @ApiModelProperty(value = "功能代码")
    private String functionCode;
    @ApiModelProperty(value = "功能名称")
    private String functionName;
    @ApiModelProperty(value = "上级编号")
    private String parentFunctionId;
    @ApiModelProperty(value = "功能级别")
    private int functionLevel;
    @ApiModelProperty(value = "URL")
    private String functionUrl;
    @ApiModelProperty(value = "页面路由")
    private String routePage;
    @ApiModelProperty(value = "是否是菜单Y是/N否")
    private IsMenu isMenu;
    @ApiModelProperty(value = "子级菜单")
    private List<FunctionTreeVO> functionTreeVOS;
    @ApiModelProperty(value = "角色ID")
    private Integer roleId;
    @ApiModelProperty(value = "菜单权限是否选中")
    private Integer checked;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getParentFunctionId() {
        return parentFunctionId;
    }

    public void setParentFunctionId(String parentFunctionId) {
        this.parentFunctionId = parentFunctionId;
    }

    public int getFunctionLevel() {
        return functionLevel;
    }

    public void setFunctionLevel(int functionLevel) {
        this.functionLevel = functionLevel;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public String getRoutePage() {
        return routePage;
    }

    public void setRoutePage(String routePage) {
        this.routePage = routePage;
    }

    public IsMenu getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(IsMenu isMenu) {
        this.isMenu = isMenu;
    }

    public List<FunctionTreeVO> getFunctionTreeVOS() {
        return functionTreeVOS;
    }

    public void setFunctionTreeVOS(List<FunctionTreeVO> functionTreeVOS) {
        this.functionTreeVOS = functionTreeVOS;
    }
}
