package com.qifenqian.bms.v2.biz.basedata.certify.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.certify.bean.Certify;
import com.qifenqian.bms.basemanager.certify.mapper.CertifyMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/26 16:03
 */
@Service
public class BaseCertifyService extends BaseService {
    @Autowired
    private CertifyMapper certifyMapper;

    public PageInfo<Certify> findCertifyList(Certify certify) {
        List<Certify> certifies = certifyMapper.selectCertifys(certify);
        return new PageInfo<>(certifies);
    }

    public ResultData add(Certify certify) {
        Certify queryBean = new Certify();
        queryBean.setCertifyType(certify.getCertifyType());
        List<Certify> certifyList = certifyMapper.selectCertifys(queryBean);
        if (CollectionUtils.isNotEmpty(certifyList)) {
            throw new BizException("证件代码已存在!");
        }
        int result = this.certifyMapper.insertCertify(certify);
        if (result < 1) {
            throw new BizException("添加保存证件异常!");
        }
        return ResultData.success();
    }

    public ResultData update(Certify certify) {
        try {
            this.certifyMapper.updateCertify(certify);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("修改证件信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(Certify certify) {
        try {
            this.certifyMapper.deleteBankByBankCode(certify.getCertifyType());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("删除证件信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }
}
