package com.qifenqian.bms.v2.biz.customer.photo.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.photo.bean.CertificateAuth;
import com.qifenqian.bms.basemanager.photo.bean.Photo;
import com.qifenqian.bms.basemanager.photo.mapper.PhotoMapper;
import com.qifenqian.bms.basemanager.photo.service.PhotoService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author LvFeng
 * @Description: 照片审核服务层
 * @date 2020/4/27 18:43
 */
@Service
public class BizPhotoService extends BaseService {
    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private PhotoService photoService;

    public PageInfo<CertificateAuth> list(CertificateAuth queryBean) {
        List<CertificateAuth> list = photoMapper.list(queryBean);
        return new PageInfo<>(list);
    }

    public Photo queryCertificate(CertificateAuth queryBean) {
        if (queryBean.getCustType().equals("0")) {
            Photo photo = photoMapper.selectCustCertificate(queryBean.getAuthId());
            if (photo != null) {
                return photo;
            } else {
                throw new BizException("此用户证件不存在！");
            }
        } else {
            throw new BizException("暂不支持");
        }
    }

    public void showPicture(HttpServletRequest request, HttpServletResponse response, String path) {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            ips = new FileInputStream(new File(path.replaceAll("\\\\", "\\\\\\\\")));
            response.setContentType("image/*");
            out = response.getOutputStream();
            int i = ips.available();// 文件大小
            byte[] date = new byte[i];
            ips.read(date);
            ips.close();
            out.write(date);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
    }

    public ResultData audit(CurrentAccount currentAccount, CertificateAuth certificate) {
        ResultData resultData = null;
        try {
            User user = WebUtils.getUserInfo();
            certificate.setModifyId(String.valueOf(currentAccount.getLoginId()));
            certificate.setCertifyUser(String.valueOf(currentAccount.getLoginId()));
            if (certificate.getCertificateState().equals("0")) {
                String result = photoService.auditYes(certificate);
                if ("SUCCESS".equals(result)) {
                    resultData = ResultData.success();
                } else {
                    resultData = ResultData.error();
                }
            } else if (certificate.getCertificateState().equals("2")) {
                photoService.auditNo(certificate);
                resultData = ResultData.success();
            }
        } catch (Exception e) {
            logger.error("请求更新审核信息异常", e);
            throw new BizException(e.getMessage());
        }
        return resultData;
    }

}
