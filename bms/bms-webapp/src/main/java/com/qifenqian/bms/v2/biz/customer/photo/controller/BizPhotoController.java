package com.qifenqian.bms.v2.biz.customer.photo.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.photo.bean.CertificateAuth;
import com.qifenqian.bms.basemanager.photo.bean.Photo;
import com.qifenqian.bms.v2.biz.customer.photo.service.BizPhotoService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LvFeng
 * @Description: 证件审核管理
 * @date 2020/4/27 18:43
 */
@RestController
@Api(tags = "证件审核")
public class BizPhotoController extends BaseController {
    @Autowired
    private BizPhotoService bizPhotoService;

    @PostMapping("/customer/photo/list")
    @ApiOperation(value = "证件审核列表")
    public PageInfo<CertificateAuth> list(PageQuery query, @RequestBody CertificateAuth queryBean) {
        return bizPhotoService.list(queryBean);
    }

    @PostMapping("/customer/photo/queryCertificate")
    @ApiOperation(value = "用户证件详情")
    public Photo queryCertificate(@RequestBody CertificateAuth queryBean) {
        return bizPhotoService.queryCertificate(queryBean);
    }

    @PostMapping("/customer/photo/show")
    @ApiOperation(value = "读取图片")
    public void show(HttpServletRequest request, HttpServletResponse response, String path) {
        bizPhotoService.showPicture(request, response, path);
    }

    @PostMapping("/customer/photo/audit")
    @ApiOperation(value = "证件审核")
    public ResultData audit(CurrentAccount currentAccount, @RequestBody CertificateAuth certificate) {
        if (null == certificate) {
            throw new BizException("证件审核对象为空");
        }
        if (StringUtils.isBlank(certificate.getCustId())) {
            throw new BizException("用户编号为空");
        }
        if (certificate.getAuthId() < 1) {
            throw new BizException("审核编号为空");
        }
        if (StringUtils.isBlank(certificate.getCertificateState())) {
            throw new BizException("审核认证状态为空");
        }
        if (StringUtils.isBlank(certificate.getCustName())) {
            throw new BizException("用户姓名为空");
        }
        return bizPhotoService.audit(currentAccount, certificate);
    }

}
