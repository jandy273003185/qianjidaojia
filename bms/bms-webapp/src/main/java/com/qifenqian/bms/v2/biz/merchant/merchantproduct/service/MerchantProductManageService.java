package com.qifenqian.bms.v2.biz.merchant.merchantproduct.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.product.bean.MerchantProduct;
import com.qifenqian.bms.merchant.product.mapper.MerchantProductMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MerchantProductManageService extends BaseService {
    /**
     * 默认开通状态为01待审核
     */
    private static final String DEFAULT_PRODUCT_STATUS = "01";
    /**
     * 开通成功 00 审核通过
     */
    private static final String SUCCESS_PRODUCT_STATUS = "00";

    @Autowired
    private MerchantProductMapper merchantProductMapper;

    public PageInfo<MerchantProduct> list(MerchantProduct queryBean) {
        List<MerchantProduct> merchantProducts = merchantProductMapper.selectMerchantProductList(queryBean);
        return new PageInfo<>(merchantProducts);
    }


    public ResultData add(MerchantProduct merchantProduct) {
        //开通默认09,待审核
        merchantProduct.setProductStatus(DEFAULT_PRODUCT_STATUS);
        MerchantProduct isExist = merchantProductMapper.selectMerchantProductByCode(merchantProduct);
        if (null != isExist)
            throw new BizException("该商户产品已经存在！");
        try {
            merchantProduct.setId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + GenSN.getRandomNum(18));
            merchantProductMapper.insertMerchantProductSingle(merchantProduct);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(MerchantProduct merchantProduct) {
        //查询需要修改的商户产品信息是否存在
        MerchantProduct isExist = merchantProductMapper.selectMerchantProductByCode(merchantProduct);
        if (null == isExist)
            throw new BizException("该商户产品不存在！");
        try {
            merchantProductMapper.updateMerchantProduct(merchantProduct);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData audit(MerchantProduct merchantProduct) {
        merchantProduct.setProductStatus(SUCCESS_PRODUCT_STATUS);
        return update(merchantProduct);
    }

    public ResultData delete(MerchantProduct merchantProduct) {
        try {
            merchantProductMapper.deleteMerchantProduct(merchantProduct);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }


}
