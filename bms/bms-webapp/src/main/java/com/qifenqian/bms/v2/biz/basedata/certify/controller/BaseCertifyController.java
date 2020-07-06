package com.qifenqian.bms.v2.biz.basedata.certify.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.certify.bean.Certify;
import com.qifenqian.bms.basemanager.certify.bean.FileBean;
import com.qifenqian.bms.basemanager.certify.bean.IdentityDetailBean;
import com.qifenqian.bms.basemanager.certify.service.CertifyService;
import com.qifenqian.bms.v2.biz.basedata.certify.bean.domain.DealIdentityAO;
import com.qifenqian.bms.v2.biz.basedata.certify.service.BaseCertifyService;
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

import java.util.List;

/**
 * @author LiBin
 * @Description: 证件信息
 * @date 2020/4/26 15:59
 */
@RestController
@Api(tags = "证件信息管理")
public class BaseCertifyController extends BaseController {

    @Autowired
    private CertifyService certifyService;
    @Autowired
    private BaseCertifyService baseCertifyService;


    @PostMapping(value = "/certify/list")
    @ApiOperation("证件信息列表")
    public PageInfo<Certify> list(PageQuery pageQuery, @RequestBody Certify certify) {
        return this.baseCertifyService.findCertifyList(certify);
    }

    @PostMapping(value = "/certify/add")
    @ApiOperation("证件信息添加")
    public ResultData add(@RequestBody Certify certify) {
        if (StringUtils.isBlank(certify.getCertifyName())) {
            throw new BizException("证件名称为空");
        }

        if (StringUtils.isBlank(certify.getCertifyType())) {
            throw new BizException("证件类型为空");
        }
        return this.baseCertifyService.add(certify);
    }

    @PostMapping(value = "/certify/update")
    @ApiOperation("证件信息更新")
    public ResultData update(@RequestBody Certify certify) {
        if (StringUtils.isBlank(certify.getCertifyName())) {
            throw new BizException("证件名称为空");
        }

        if (StringUtils.isBlank(certify.getCertifyType())) {
            throw new BizException("证件类型为空");
        }
        return this.baseCertifyService.update(certify);
    }

    @PostMapping(value = "/certify/delete")
    @ApiOperation("证件信息删除")
    public ResultData delete(@RequestBody Certify certify) {
        if (StringUtils.isBlank(certify.getCertifyType())) {
            throw new BizException("证件类型为空");
        }
        return this.baseCertifyService.delete(certify);
    }

    @PostMapping(value = "/certify/fileList")
    @ApiOperation("查询文件信息")
    public List<FileBean> fileList(@RequestBody FileBean queryBean) {
        return certifyService.getFileList(queryBean);
    }

    @PostMapping(value = "/certify/identityDetail")
    @ApiOperation("查询验证明细")
    public List<IdentityDetailBean> identityDetail(@RequestBody IdentityDetailBean queryBean) {
        return certifyService.getIdentityDetail(queryBean);
    }

    @PostMapping(value = "/certify/deal")
    @ApiOperation("处理验证明细")
    public ResultData deal(CurrentAccount currentAccount, @RequestBody DealIdentityAO queryBean) {
        try {
            certifyService.dealValidateDetail(queryBean.getValidateId(), queryBean.getFileId(), queryBean.getMemo(), currentAccount.getLoginId().toString());
        } catch (Exception e) {
            throw new BizException("请求处理验证明细异常!");
        }
        return ResultData.success();
    }
}
