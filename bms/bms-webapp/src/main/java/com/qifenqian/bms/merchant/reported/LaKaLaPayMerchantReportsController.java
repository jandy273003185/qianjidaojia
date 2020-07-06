package com.qifenqian.bms.merchant.reported;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.merchant.reported.bean.TdLakalaBankAreaInfo;
import com.qifenqian.bms.merchant.reported.bean.TdLakalaMccInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.LaKaLaPayMerchantReportsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author LvFeng
 * @Description: 拉卡拉报备
 * @date 2020/5/21 11:49
 */
@Controller
@RequestMapping("/merchant")
public class LaKaLaPayMerchantReportsController {
    private Logger logger = LoggerFactory.getLogger(LaKaLaPayMerchantReportsController.class);

    @Autowired
    private FmIncomeMapperDao fmIncomeMapperDao;

    @Autowired
    private MerchantEnterService merchantEnterService;

    @Autowired
    private LaKaLaPayMerchantReportsService laKaLaPayMerchantReportsService;

    @RequestMapping("/reported/laKaLaPayMerchantReporteds")
    public ModelAndView viewMerchantReported(String channlCode, String merchantCode, String status) {
        if (StringUtils.isBlank(channlCode)) {
            throw new IllegalArgumentException("渠道编码不能为空！");
        }
        if (StringUtils.isBlank(merchantCode)) {
            throw new IllegalArgumentException("商户编码不能为空！");
        }
        ModelAndView mv = new ModelAndView();
        //商户信息
        TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
        mv.addObject("custInfo", custInfo);
        //@TODO 银行信息

        //行业信息
        List<TdLakalaMccInfo> tdLakalaMccInfos = laKaLaPayMerchantReportsService.selectLakalaMccInfo(null);
        mv.addObject("mccInfos", tdLakalaMccInfos);
        //银行地区信息
        List<TdLakalaBankAreaInfo> tdLakalaBankAreaInfos = laKaLaPayMerchantReportsService.selectLakalaBankAreaInfo(null);
        mv.addObject("bankAreaInfos", tdLakalaBankAreaInfos);
        //获取图片路径
        MerchantVo merchantVo = new MerchantVo();
        merchantVo.setAuthId(custInfo.getAuthId());
        merchantVo.setCustId(custInfo.getCustId());
        PicturePath picturePath = merchantEnterService.getPicPath(merchantVo);
        mv.addObject("picturePathVo", picturePath);
        return mv;
    }
}
