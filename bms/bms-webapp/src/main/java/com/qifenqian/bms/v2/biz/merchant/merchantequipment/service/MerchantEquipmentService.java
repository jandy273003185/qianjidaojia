package com.qifenqian.bms.v2.biz.merchant.merchantequipment.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.materiel.bean.Materiel;
import com.qifenqian.bms.materiel.mapper.MaterielMapper;
import com.qifenqian.bms.merchant.equipment.bean.DeviceLogin;
import com.qifenqian.bms.merchant.equipment.bean.MerchantSign;
import com.qifenqian.bms.merchant.equipment.mapper.MerchantSignMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantEquipmentService extends BaseService {
    /**
     * 商户公钥
     */
    private static final String MERCHANT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYB797teRacGp9qlEEURlvl9ZHaDRYijWbG3vpRmfZ4XjJT55yL/UDO7DjqQBe23g1bmw6tUDIa8aHTEVvLzuy9kx8SDfKM4sz9k6u8dE4SnNvr6YlSz++iWr80p+alwh7yVAywrg3RAwaOOdOHtna2FcwBHqMBVQb7wWEP46AFwIDAQAB";

    @Autowired
    private MerchantSignMapper merchantSignMapper;
    @Autowired
    private MaterielMapper materielMapper;

    public PageInfo<MerchantSign> list(MerchantSign queryBean) {
        List<MerchantSign> merchantProducts = merchantSignMapper.selectMerchantSignList(queryBean);
        return new PageInfo<>(merchantProducts);
    }

    public PageInfo<DeviceLogin> newList(DeviceLogin queryBean) {
        List<DeviceLogin> deviceLoginList = merchantSignMapper.selectDeviceLoginById(queryBean);
        return new PageInfo<>(deviceLoginList);
    }


    public ResultData add(MerchantSign merchantSign) {
        MerchantSign isExist = merchantSignMapper.selectMerchantSignByMerIdAndTerNo(merchantSign);
        if (null != isExist)
            throw new BizException("该商户设备已经存在！");
        Materiel materiel = materielMapper.selectMaterielByMachineId(merchantSign.getTerminalNo());
        if (null == materiel)
            throw new BizException("该设备编号:" + merchantSign.getTerminalNo() + "不存在！");
        try {
            merchantSign.setMerchantPublicKey(MERCHANT_PUBLIC_KEY);
            merchantSignMapper.insertMerchantSign(merchantSign);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(MerchantSign merchantSign) {
        MerchantSign isExist = merchantSignMapper.selectMerchantSignById(merchantSign.getId());
        if (null == isExist)
            throw new BizException("该商户设备不存在！");
        Materiel materiel = materielMapper.selectMaterielByMachineId(merchantSign.getTerminalNo());
        if (null == materiel)
            throw new BizException("该设备编号:" + merchantSign.getTerminalNo() + "不存在！");
        MerchantSign merSign = merchantSignMapper.selectMerchantSignByMerIdAndTerNo(merchantSign);
        if (null != merSign && !(merSign.getId().equals(merchantSign.getId()))) {
            throw new BizException("商户编号为：" + merchantSign.getMerchantId() + "，设备编号为：" + merchantSign.getTerminalNo() + "的商户设备信息已经存在");
        }
        try {
            merchantSignMapper.updateMerchantSign(merchantSign);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }


    public ResultData delete(MerchantSign merchantSign) {
        try {
            merchantSignMapper.deleteMerchantSign(merchantSign.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(e.getMessage());
        }
        return ResultData.success();
    }
}
