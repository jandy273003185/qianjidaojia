package com.qifenqian.bms.v2.biz.merchant.store.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;
import com.qifenqian.bms.v2.biz.merchant.store.service.BizStoreManageService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author LiBin
 * @Description: 门店管理
 * @date 2020/4/17 17:53
 */
@RestController
@Api(tags = "门店管理")
public class BizStoreManageController extends BaseController {

    @Autowired
    private BizStoreManageService bizStoreManageService;

    @PostMapping(value = "/storeManage/list")
    @ApiOperation(value = "门店列表")
    public PageInfo<StoreManage> list(PageQuery pageQuery, @RequestBody StoreManage queryBean) {
        PageInfo<StoreManage> pageInfo = this.bizStoreManageService.findStoreManageList(queryBean);
        return pageInfo;
    }

    @PostMapping(value = "/storeManage/merchantList")
    @ApiOperation(value = "门店商户选择列表")
    public List<Merchant> list() {
        return this.bizStoreManageService.findMerchantList();
    }

    @PostMapping(value = "/storeManage/add")
    @ApiOperation(value = "添加门店")
    public ResultData add(CurrentAccount currentAccount, @RequestBody StoreManage storeManage) {
        storeManage.setCreateId(currentAccount.getLoginId().toString());
        return this.bizStoreManageService.add(storeManage);
    }

    @PostMapping(value = "/storeManage/update")
    @ApiOperation(value = "修改门店")
    public ResultData update(CurrentAccount currentAccount, @RequestBody StoreManage storeManage) {
        storeManage.setModifyId(currentAccount.getLoginId().toString());
        return this.bizStoreManageService.update(storeManage);
    }

    @PostMapping(value = "/storeManage/delete")
    @ApiOperation(value = "删除门店")
    public ResultData delete(@RequestBody StoreManage storeManage) {
        return this.bizStoreManageService.delete(storeManage);
    }

    @PostMapping(value = "/storeManage/verify")
    @ApiOperation(value = "校验存着")
    public ResultData verify(@RequestBody StoreManage storeManage) {
        return this.bizStoreManageService.verify(storeManage);
    }

    @PostMapping(value = "/storeManage/repeat")
    @ApiOperation(value = "校验重复")
    public ResultData repeat(@RequestBody StoreManage storeManage) {
        return this.bizStoreManageService.repeat(storeManage);
    }

    @PostMapping(value = "/storeManage/getQRCode")
    @ApiOperation(value = "获取二维码")
    public ResultData getQRCode(@RequestBody StoreManage storeManage) {
        return this.bizStoreManageService.getQRCode(storeManage);
    }

    @PostMapping(value = "/storeManage/getNewPic")
    @ApiOperation(value = "获取新图片")
    public void getNewPic(@RequestBody StoreManage storeManage, HttpServletResponse response) throws IOException {
        this.bizStoreManageService.getNewPic(storeManage, response);
    }

    @PostMapping(value = "/storeManage/downNewPic")
    @ApiOperation(value = "下载新图片")
    public void downNewPic(@RequestBody StoreManage storeManage, HttpServletResponse response) throws IOException {
        this.bizStoreManageService.downNewPic(storeManage, response);
    }
}
