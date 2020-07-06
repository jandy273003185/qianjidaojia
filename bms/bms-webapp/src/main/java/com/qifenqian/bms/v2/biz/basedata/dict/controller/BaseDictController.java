package com.qifenqian.bms.v2.biz.basedata.dict.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.dictData.bean.DictData;
import com.qifenqian.bms.v2.biz.basedata.dict.service.BaseDictService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 数据字典
 * @date 2020/4/27 09:43
 */
@RestController
@Api(tags = "数据字典管理")
public class BaseDictController extends BaseController {

    @Autowired
    private BaseDictService baseDictService;

    @PostMapping(value = "/dict/list")
    @ApiOperation("数据字典列表")
    public PageInfo<DictData> list(PageQuery pageQuery, @RequestBody DictData dictData) {
        return this.baseDictService.findDistDataList(dictData);
    }

    @PostMapping(value = "/dict/add")
    @ApiOperation("数据字典添加")
    public ResultData add(CurrentAccount currentAccount, @RequestBody DictData dictData) {
        dictData.setCreator(currentAccount.getLoginId().toString());
        return this.baseDictService.add(dictData);
    }

    @PostMapping(value = "/dict/update")
    @ApiOperation("数据字典修改")
    public ResultData update(CurrentAccount currentAccount, @RequestBody DictData dictData) {
        dictData.setLastupdateUser(currentAccount.getLoginId().toString());
        return this.baseDictService.update(dictData);
    }

    @PostMapping(value = "/dict/delete")
    @ApiOperation("数据字典修改")
    public ResultData delete(@RequestBody DictData dictData) {
        if (StringUtils.isBlank(dictData.getId())) {
            throw new BizException("Id不能为空!");
        }
        return this.baseDictService.delete(dictData);
    }
}
